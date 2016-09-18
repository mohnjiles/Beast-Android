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

import xyz.jtmiles.beastforgw2.R
import xyz.jtmiles.beastforgw2.services.AccountService
import xyz.jtmiles.beastforgw2.util.Utils
import xyz.jtmiles.beastforgw2.util.bindView
import retrofit2.Callback
import retrofit2.Response
import xyz.jtmiles.beastforgw2.adapters.TitlesAdapter
import xyz.jtmiles.beastforgw2.models.Title


class TitlesFragment : Fragment() {

    val rvTitles: RecyclerView by bindView(R.id.rvTitles)
    val pbLoading: ProgressBar by bindView(R.id.pbLoading)

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater!!.inflate(R.layout.fragment_titles, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        rvTitles.setHasFixedSize(true)
        rvTitles.layoutManager = LinearLayoutManager(activity)

        val accountService = Utils.getRetrofit(true).create(AccountService::class.java)
        accountService.getTitleIds(Utils.getApiKeyForAuth(activity)).enqueue( object : Callback<List<Int>> {
            override fun onResponse(call: Call<List<Int>>?, response: Response<List<Int>>) {
                if (response.isSuccessful) {
                    val titleIds = response.body().joinToString(",")

                    accountService.getTitlesById(titleIds).enqueue(object: Callback<List<Title>> {
                        override fun onResponse(call: Call<List<Title>>?, response: Response<List<Title>>) {
                            if (response.isSuccessful) {
                                val titleList = response.body()
                                val adapter = TitlesAdapter(titleList.map { it.name })
                                rvTitles.adapter = adapter
                                pbLoading.visibility = View.INVISIBLE
                            }
                        }

                        override fun onFailure(call: Call<List<Title>>?, t: Throwable?) {

                        }
                    })
                }
            }

            override fun onFailure(call: Call<List<Int>>?, t: Throwable?) {

            }

        })
    }

    companion object {
        fun newInstance() : TitlesFragment {
            val fragment = TitlesFragment()
            return fragment
        }
    }
}
