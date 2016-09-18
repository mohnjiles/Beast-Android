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
import xyz.jtmiles.beastforgw2.adapters.MinisAdapter
import xyz.jtmiles.beastforgw2.models.Mini
import xyz.jtmiles.beastforgw2.services.AccountService
import xyz.jtmiles.beastforgw2.util.Utils
import xyz.jtmiles.beastforgw2.util.bindView

class MinisFragment : Fragment() {

    val rvMinis: RecyclerView by bindView(R.id.rvMinis)
    val pbLoading: ProgressBar by bindView(R.id.pbLoading)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.fragment_minis, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        rvMinis.setHasFixedSize(true)
        rvMinis.layoutManager = LinearLayoutManager(activity)

        val accountService = Utils.getRetrofit(true).create(AccountService::class.java)
        accountService.getMinisIds(Utils.getApiKeyForAuth(activity)).enqueue( object : Callback<List<Int>> {
            override fun onResponse(call: Call<List<Int>>?, response: Response<List<Int>>) {
                if (response.isSuccessful) {
                    val miniIds = response.body().joinToString(",")
                    accountService.getMinis(miniIds).enqueue( object: Callback<List<Mini>> {

                        override fun onResponse(call: Call<List<Mini>>?, response: Response<List<Mini>>) {
                            if (response.isSuccessful) {
                                val miniList = response.body().sortedBy { it.order }
                                rvMinis.adapter = MinisAdapter(miniList)
                                pbLoading.visibility = View.INVISIBLE
                            }
                        }

                        override fun onFailure(call: Call<List<Mini>>?, t: Throwable) {

                        }

                    })
                }
            }

            override fun onFailure(call: Call<List<Int>>?, t: Throwable?) {
            }

        })
    }

    companion object {
        fun newInstance() : MinisFragment {
            return MinisFragment()
        }
    }

}
