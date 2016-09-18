package xyz.jtmiles.beastforgw2.services

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query
import xyz.jtmiles.beastforgw2.models.*

interface AccountService {
    @GET("account")
    fun getAccount(@Header("Authorization") auth: String): Call<Account>

    @GET("account/wallet")
    fun getWallet(@Header("Authorization") auth: String): Call<List<Wallet>>

    @GET("account/bank")
    fun getBank(@Header("Authorization") auth: String): Call<List<Inventory>>

    @GET("account/titles")
    fun getTitleIds(@Header("Authorization") auth: String): Call<List<Int>>

    @GET("titles")
    fun getTitlesById(@Query("ids") commaSeparatedIds: String) : Call<List<Title>>

    @GET("account/finishers")
    fun getFinisherIds(@Header("Authorization") auth:String) : Call<List<AccountFinisher>>

    @GET("finishers")
    fun getFinishersById(@Query("ids") commaSeparatedIds: String) : Call<List<Finisher>>

    @GET("account/minis")
    fun getMinisIds(@Header("Authorization") auth: String) : Call<List<Int>>

    @GET("minis")
    fun getMinis(@Query("ids") commaSeparatedIds: String) : Call<List<Mini>>

}
