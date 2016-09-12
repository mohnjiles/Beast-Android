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
import xyz.jtmiles.beastforgw2.adapters.FinishersAdapter
import xyz.jtmiles.beastforgw2.models.AccountFinisher
import xyz.jtmiles.beastforgw2.models.Finisher
import xyz.jtmiles.beastforgw2.services.AccountService
import xyz.jtmiles.beastforgw2.util.Utils
import xyz.jtmiles.beastforgw2.util.bindView

class FinisherFragment : Fragment() {

    val rvFinishers: RecyclerView by bindView(R.id.rvFinishers)
    val pbLoading: ProgressBar by bindView(R.id.pbLoading)

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater!!.inflate(R.layout.fragment_finisher, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        rvFinishers.setHasFixedSize(true)
        rvFinishers.layoutManager = LinearLayoutManager(activity)

        val accountService = Utils.getRetrofit(true).create(AccountService::class.java)
        accountService.getFinisherIds(Utils.getApiKeyForAuth(activity)).enqueue(object: Callback<List<AccountFinisher>> {
            override fun onResponse(call: Call<List<AccountFinisher>>?, response: Response<List<AccountFinisher>>) {
                if (response.isSuccessful) {
                    val accountFinishers = response.body()
                    val idString = accountFinishers.map { it.id }.joinToString(",")

                    accountService.getFinishersById(idString).enqueue(object: Callback<List<Finisher>> {
                        override fun onResponse(call: Call<List<Finisher>>?, response: Response<List<Finisher>>) {
                            if (response.isSuccessful) {
                                val finisherList = response.body().sortedBy { it.order }
                                rvFinishers.adapter = FinishersAdapter(finisherList)

                                pbLoading.visibility = View.INVISIBLE
                            }
                        }

                        override fun onFailure(call: Call<List<Finisher>>?, t: Throwable?) {
                        }

                    })

                }
            }

            override fun onFailure(call: Call<List<AccountFinisher>>?, t: Throwable?) {
            }

        })
    }

    companion object {
        fun newInstance() : FinisherFragment {
            val fragment = FinisherFragment()
            return fragment
        }
    }
}
