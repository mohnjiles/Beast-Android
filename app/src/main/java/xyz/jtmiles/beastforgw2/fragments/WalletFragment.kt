package xyz.jtmiles.beastforgw2.fragments


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import xyz.jtmiles.beastforgw2.R
import xyz.jtmiles.beastforgw2.adapters.WalletAdapter
import xyz.jtmiles.beastforgw2.managers.StorageManager
import xyz.jtmiles.beastforgw2.models.CachedWalletList
import xyz.jtmiles.beastforgw2.models.Wallet
import xyz.jtmiles.beastforgw2.services.AccountService
import xyz.jtmiles.beastforgw2.util.Utils
import xyz.jtmiles.beastforgw2.util.bindView
import java.util.*


class WalletFragment : Fragment() {


    val rvWallet: RecyclerView by bindView(R.id.rvWallet)
    val pbLoading: ProgressBar by bindView(R.id.pbLoading)

    override fun onCreate(savedInstanceState: Bundle?) = super.onCreate(savedInstanceState)

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? =
            inflater!!.inflate(R.layout.fragment_wallet, container, false)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val storageManager = StorageManager(activity)
        val layoutManager = LinearLayoutManager(activity)
        rvWallet.layoutManager = layoutManager

        var cachedWallet: CachedWalletList? = null
        try {
            cachedWallet = storageManager.loadWallet()
        } catch (ex: Exception){
            Log.d("WalletFragment", ex.message)
        }

        if (cachedWallet != null && cachedWallet.wallet.size > 0
                && (Date().time - cachedWallet.lastUpdated.time) < 15*60*1000) {
            Log.d("WalletFragment", "Loading from cache")
            rvWallet.visibility = View.VISIBLE
            pbLoading.visibility = View.GONE

            val adapter = WalletAdapter(activity, cachedWallet.wallet)
            rvWallet.adapter = adapter
        } else {
            Log.d("WalletFragment", "Loading from web")

            val retrofit = Utils.getRetrofit(true)
            val accountService = retrofit.create(AccountService::class.java)
            accountService.getWallet(Utils.getApiKeyForAuth(activity)).enqueue(object : Callback<List<Wallet>> {
                override fun onResponse(call: Call<List<Wallet>>, response: Response<List<Wallet>>) {
                    if (response.isSuccessful) {
                        val wallet = response.body()

                        rvWallet.visibility = View.VISIBLE
                        pbLoading.visibility = View.GONE

                        val adapter = WalletAdapter(activity, wallet)
                        rvWallet.adapter = adapter

                        storageManager.saveWallet(wallet)
                    }


                }

                override fun onFailure(call: Call<List<Wallet>>, t: Throwable) {

                }
            })
        }
    }

    companion object {

        fun newInstance(): WalletFragment {
            return WalletFragment()
        }
    }
}// Required empty public constructor
