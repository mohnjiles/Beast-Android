package xyz.jtmiles.beastforgw2.fragments


import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import xyz.jtmiles.beastforgw2.ItemOffsetDecoration
import xyz.jtmiles.beastforgw2.R
import xyz.jtmiles.beastforgw2.activities.ItemDetailActivity
import xyz.jtmiles.beastforgw2.adapters.InventoryAdapter
import xyz.jtmiles.beastforgw2.managers.StorageManager
import xyz.jtmiles.beastforgw2.models.CachedBankItems
import xyz.jtmiles.beastforgw2.models.CachedInventory
import xyz.jtmiles.beastforgw2.models.Inventory
import xyz.jtmiles.beastforgw2.models.Item
import xyz.jtmiles.beastforgw2.services.AccountService
import xyz.jtmiles.beastforgw2.services.ItemService
import xyz.jtmiles.beastforgw2.util.RecyclerItemClickListener
import xyz.jtmiles.beastforgw2.util.Utils
import xyz.jtmiles.beastforgw2.util.bindView
import java.util.*

class BankFragment : Fragment() {

    val rvBank: RecyclerView by bindView(R.id.rvBank)
    val pbLoading: ProgressBar by bindView(R.id.pbLoading)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater!!.inflate(R.layout.fragment_bank, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val orientation = resources.configuration.orientation
        val storageManager = StorageManager(activity)

        if (orientation == Configuration.ORIENTATION_PORTRAIT)
            rvBank.layoutManager = GridLayoutManager(activity, 4)
        else rvBank.layoutManager = GridLayoutManager(activity, 6)

        val itemDecoration = ItemOffsetDecoration(activity, R.dimen.grid_padding)
        rvBank.addItemDecoration(itemDecoration)

        var cachedBank: CachedInventory? = null
        try {
            cachedBank = storageManager.loadBank()
        } catch (ex: Exception) {
            Log.w("BankFragment", "Error loading bank")
        }

        var cachedBankItems: CachedBankItems? = null
        try {
            cachedBankItems = storageManager.loadBankItems()
        } catch (ex: Exception) {
            Log.w("BankFragment", "Error loading bank")
        }

        if (cachedBank != null && cachedBankItems != null && cachedBankItems.bankItems.size > 0
                && (Date().time - cachedBank.lastUpdated.time) <  5 * 60 * 1000) {

            Log.d("BankFragment", "Loading from cache")

            val bankList = cachedBank.inventory

            val sb = StringBuilder()

            for ((index, bankItem) in bankList.withIndex()) {
                sb.append(bankItem.id)
                if (index < bankList.size - 1) {
                    sb.append(",")
                }
            }

            val adapter = InventoryAdapter(activity, bankList, cachedBankItems.bankItems)
            rvBank.adapter = adapter
            rvBank.addOnItemTouchListener(RecyclerItemClickListener(activity, RecyclerItemClickListener.OnItemClickListener { view, pos ->

                val itemList = cachedBankItems!!.bankItems
                if (itemList[pos] as Item? != null) {
                    val intent = Intent(activity, ItemDetailActivity::class.java)
                    intent.putExtra("item", itemList[pos])
                    startActivity(intent)
                }
            }))

            pbLoading.visibility = View.GONE
            rvBank.visibility = View.VISIBLE

        } else {

            Log.d("BankFragment", "Loading from web")

            val accountService = Utils.getRetrofit(true).create(AccountService::class.java)
            val itemService = Utils.getRetrofit(true).create(ItemService::class.java)
            accountService.getBank(Utils.getApiKeyForAuth(activity)).enqueue(object : Callback<List<Inventory>> {
                override fun onResponse(call: Call<List<Inventory>>, response: Response<List<Inventory>>) {
                    if (response.isSuccessful) {

                        val bankInventoryList = response.body()

                        val bankList = ArrayList(bankInventoryList)
                        bankList.removeAll(Collections.singleton(null))

                        val sb = StringBuilder()

                        for ((index, bankItem) in bankList.withIndex()) {
                            sb.append(bankItem.id)
                            if (index < bankList.size - 1) {
                                sb.append(",")
                            }
                        }

                        itemService.getItemsById(sb.toString()).enqueue(object: Callback<List<Item>>{
                            override fun onResponse(call: Call<List<Item>>?, response: Response<List<Item>>) {
                                if (response.isSuccessful) {
                                    val itemList = ArrayList(response.body())

                                    /* the GW2 items API doesn't return duplicate entries like the inventory one
                                       does when items are at stacking capacity (typically 250), so we have to add the
                                       other stack into the itemList manually
                                     */
                                    for ((index, inventory) in bankList.withIndex()) {
                                        if (inventory.id != itemList[index].id) {
                                            itemList.add(index, itemList.filter { it.id == inventory.id }.firstOrNull())
                                        }
                                    }

                                    pbLoading.visibility = View.GONE
                                    rvBank.visibility = View.VISIBLE

                                    val adapter = InventoryAdapter(activity, bankList, itemList)
                                    rvBank.adapter = adapter
                                    rvBank.addOnItemTouchListener(RecyclerItemClickListener(activity, RecyclerItemClickListener.OnItemClickListener { view, pos ->
                                        if (itemList[pos] as Item? != null) {
                                            val intent = Intent(activity, ItemDetailActivity::class.java)
                                            intent.putExtra("item", itemList[pos])
                                            startActivity(intent)
                                        }
                                    }))

                                    storageManager.saveBankItems(itemList)
                                    storageManager.saveBank(bankList)
                                }
                            }

                            override fun onFailure(call: Call<List<Item>>?, t: Throwable?) {
                                pbLoading.visibility = View.GONE
                                rvBank.visibility = View.VISIBLE
                            }

                        })
                    }
                }

                override fun onFailure(call: Call<List<Inventory>>?, t: Throwable?) {

                }
            })
        }

    }

    companion object {

        fun newInstance(): BankFragment {
            return BankFragment()
        }
    }

}
