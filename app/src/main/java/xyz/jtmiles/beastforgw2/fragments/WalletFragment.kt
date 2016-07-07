package xyz.jtmiles.beastforgw2.fragments


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import xyz.jtmiles.beastforgw2.R
import xyz.jtmiles.beastforgw2.adapters.WalletAdapter
import xyz.jtmiles.beastforgw2.models.Wallet
import xyz.jtmiles.beastforgw2.services.AccountService
import xyz.jtmiles.beastforgw2.util.Utils
import xyz.jtmiles.beastforgw2.util.bindView


class WalletFragment : Fragment() {


    val rvWallet: RecyclerView by bindView(R.id.rvWallet)
    val pbLoading: ProgressBar by bindView(R.id.pbLoading)

    override fun onCreate(savedInstanceState: Bundle?) = super.onCreate(savedInstanceState)

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? =
            inflater!!.inflate(R.layout.fragment_wallet, container, false)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val layoutManager = LinearLayoutManager(activity)
        rvWallet.layoutManager = layoutManager

        val retrofit = Utils.getRetrofit(true)
        val accountService = retrofit.create(AccountService::class.java)
        accountService.getWallet(Utils.getApiKeyForAuth(activity)).enqueue(object : Callback<List<Wallet>> {
            override fun onResponse(call: Call<List<Wallet>>, response: Response<List<Wallet>>) {
                if (response.isSuccessful) {

                    rvWallet.visibility = View.VISIBLE
                    pbLoading.visibility = View.GONE

                    val wallet = response.body()
                    val adapter = WalletAdapter(activity, wallet)
                    rvWallet.adapter = adapter

                }
            }

            override fun onFailure(call: Call<List<Wallet>>, t: Throwable) {

            }
        })
    }

    companion object {

        fun newInstance(): WalletFragment {
            return WalletFragment()
        }
    }
}// Required empty public constructor
