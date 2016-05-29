package xyz.jtmiles.beastforgw2.fragments


import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import xyz.jtmiles.beastforgw2.R
import xyz.jtmiles.beastforgw2.activities.ItemDetailActivity
import xyz.jtmiles.beastforgw2.adapters.InventoryAdapter
import xyz.jtmiles.beastforgw2.models.Character
import xyz.jtmiles.beastforgw2.models.Inventory
import xyz.jtmiles.beastforgw2.util.RecyclerItemClickListener
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

        val itemList = ArrayList<Inventory>()

        for (bag in mCharacter!!.bags) {
            if (bag == null) continue
            for (inventory in bag.inventory) {
                if (inventory != null)
                    itemList.add(inventory)
            }
        }

        val gridLayoutManager = GridLayoutManager(activity, 4)
        rvInventory.layoutManager = gridLayoutManager

        val adapter = InventoryAdapter(activity, itemList)
        rvInventory.adapter = adapter
        rvInventory.addOnItemTouchListener(RecyclerItemClickListener(activity, RecyclerItemClickListener.OnItemClickListener { view, pos ->
            val intent = Intent(activity, ItemDetailActivity::class.java)
            intent.putExtra("item", itemList[pos])
            startActivity(intent)
        }));

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
}// Required empty public constructor
