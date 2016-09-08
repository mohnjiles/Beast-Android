package xyz.jtmiles.beastforgw2.fragments


import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import xyz.jtmiles.beastforgw2.R
import xyz.jtmiles.beastforgw2.activities.ItemDetailActivity
import xyz.jtmiles.beastforgw2.adapters.InventoryAdapter
import xyz.jtmiles.beastforgw2.managers.StorageManager
import xyz.jtmiles.beastforgw2.models.*
import xyz.jtmiles.beastforgw2.services.ItemService
import xyz.jtmiles.beastforgw2.util.RecyclerItemClickListener
import xyz.jtmiles.beastforgw2.util.Utils
import xyz.jtmiles.beastforgw2.util.bindView
import java.util.*

/**
 * A simple [Fragment] subclass.
 * Use the [InventoryFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class InventoryFragment : Fragment() {

    val rvInventory: RecyclerView by bindView(R.id.rvInventory)

    private var mCharacter: Character? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
            mCharacter = arguments.getSerializable("character") as Character
        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val v = inflater!!.inflate(R.layout.fragment_inventory, container, false)
        return v
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        rvInventory.setHasFixedSize(true)
        val gridLayoutManager = GridLayoutManager(activity, 4)
        rvInventory.layoutManager = gridLayoutManager

        val storageManager = StorageManager(activity)
        var cachedInventory: CachedInventory? = null
        var cachedInventoryItems: CachedInventoryItems? = null
        try {
            cachedInventory = storageManager.loadInventory(mCharacter!!.name!!)
            cachedInventoryItems = storageManager.loadInventoryItems(mCharacter!!.name!!)
        } catch (ex: Exception) {
            Log.w("BankFragment", "Error loading bank")
        }

        if (cachedInventory != null && cachedInventoryItems != null
                && cachedInventoryItems.itemList.size > 0
                && (Date().time - cachedInventoryItems.lastUpdated.time) <  5 * 60 * 1000) {

            Log.d("InventoryFragment" , "Loading from cache")

            val inventory = cachedInventory.inventory
            val itemList = cachedInventoryItems.itemList

            val adapter = InventoryAdapter(activity, inventory, itemList)
            rvInventory.adapter = adapter
            rvInventory.addOnItemTouchListener(RecyclerItemClickListener(activity, RecyclerItemClickListener.OnItemClickListener { view, pos ->
                val intent = Intent(activity, ItemDetailActivity::class.java)
                intent.putExtra("item", itemList[pos])
                startActivity(intent)
            }))

        } else {
            Log.d("InventoryFragment" , "Loading from web")
            val inventoryList = ArrayList<Inventory>()

            for (bag in mCharacter!!.bags) {
                if (bag == null) continue
                for (inventory in bag.inventory) {
                    if (inventory != null)
                        inventoryList.add(inventory)
                }
            }

            val sb = StringBuilder()

            for ((index, item) in inventoryList.withIndex()) {
                sb.append(item.id)
                if (index < inventoryList.size - 1) {
                    sb.append(",")
                }
            }

            val itemService = Utils.getRetrofit(true).create(ItemService::class.java)
            itemService.getItemsById(sb.toString()).enqueue(object : Callback<List<Item>> {
                override fun onResponse(call: Call<List<Item>>?, response: Response<List<Item>>) {
                    if (response.isSuccessful) {
                        val itemList = ArrayList(response.body())

                        /* the GW2 items API doesn't return duplicate entries like the inventory one
                           does when items are at stacking capacity (typically 250), so we have to add the
                           other stack into the itemList manually
                        */
                        for ((index, inventory) in inventoryList.withIndex()) {
                            if (inventory.id != itemList[index].id) {
                                itemList.add(index, itemList.filter { it.id == inventory.id }.firstOrNull())
                            }
                        }

                        val adapter = InventoryAdapter(activity, inventoryList, itemList)
                        rvInventory.adapter = adapter
                        rvInventory.addOnItemTouchListener(RecyclerItemClickListener(activity, RecyclerItemClickListener.OnItemClickListener { view, pos ->
                            val intent = Intent(activity, ItemDetailActivity::class.java)
                            intent.putExtra("item", itemList[pos])
                            startActivity(intent)
                        }))

                        storageManager.saveInventoryItems(itemList, mCharacter!!.name!!)
                        storageManager.saveInventory(inventoryList, mCharacter!!.name!!)
                    }
                }

                override fun onFailure(call: Call<List<Item>>?, t: Throwable?) {
                }
            })
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Glide.get(activity).clearMemory()
    }

    companion object {

        fun newInstance(character: Character): InventoryFragment {
            val fragment = InventoryFragment()
            val args = Bundle()
            args.putSerializable("character", character)
            fragment.arguments = args
            return fragment
        }
    }
}
