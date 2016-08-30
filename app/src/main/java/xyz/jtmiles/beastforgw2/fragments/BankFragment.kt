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
import xyz.jtmiles.beastforgw2.models.Inventory
import xyz.jtmiles.beastforgw2.services.AccountService
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

        var cachedBank: CachedBankItems? = null
        try {
            cachedBank = storageManager.loadBank()
        } catch (ex: Exception) {
            Log.w("BankFragment", "Error loading bank")
        }

        if (cachedBank != null && cachedBank.bankItems.size > 0
                && (Date().time - cachedBank.lastUpdated.time) < 15*60*1000) {

            Log.d("BankFragment", "Loading from cache")

            pbLoading.visibility = View.GONE
            rvBank.visibility = View.VISIBLE

            val adapter = InventoryAdapter(activity, cachedBank.bankItems)
            rvBank.adapter = adapter
            rvBank.addOnItemTouchListener(RecyclerItemClickListener(activity, RecyclerItemClickListener.OnItemClickListener { view, pos ->

                val inventoryList = cachedBank!!.bankItems
                if (inventoryList[pos] as Inventory? != null) {
                    val intent = Intent(activity, ItemDetailActivity::class.java)
                    intent.putExtra("item", inventoryList[pos])
                    startActivity(intent)
                }
            }))
        } else {

            Log.d("BankFragment", "Loading from web")

            val accountService = Utils.getRetrofit(true).create(AccountService::class.java)
            accountService.getBank(Utils.getApiKeyForAuth(activity)).enqueue(object : Callback<List<Inventory>> {
                override fun onResponse(call: Call<List<Inventory>>, response: Response<List<Inventory>>) {
                    if (response.isSuccessful) {

                        pbLoading.visibility = View.GONE
                        rvBank.visibility = View.VISIBLE

                        val bankList = ArrayList(response.body())
                        bankList.removeAll(Collections.singleton(null))
                        val adapter = InventoryAdapter(activity, bankList)
                        rvBank.adapter = adapter
                        rvBank.addOnItemTouchListener(RecyclerItemClickListener(activity, RecyclerItemClickListener.OnItemClickListener { view, pos ->

                            val inventoryList = response.body()
                            if (inventoryList[pos] as Inventory? != null) {
                                val intent = Intent(activity, ItemDetailActivity::class.java)
                                intent.putExtra("item", inventoryList[pos])
                                startActivity(intent)
                            }
                        }))

                        storageManager.saveBank(response.body())
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
