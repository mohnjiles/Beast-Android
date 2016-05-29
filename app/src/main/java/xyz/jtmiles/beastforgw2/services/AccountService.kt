package xyz.jtmiles.beastforgw2.services

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import xyz.jtmiles.beastforgw2.models.Account
import xyz.jtmiles.beastforgw2.models.Inventory
import xyz.jtmiles.beastforgw2.models.Wallet

interface AccountService {
    @GET("account")
    fun getAccount(@Header("Authorization") auth: String): Call<Account>

    @GET("account/wallet")
    fun getWallet(@Header("Authorization") auth: String): Call<List<Wallet>>

    @GET("account/bank")
    fun getBank(@Header("Authorization") auth: String): Call<List<Inventory>>

}
