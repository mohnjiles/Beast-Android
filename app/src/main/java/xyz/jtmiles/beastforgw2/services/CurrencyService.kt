package xyz.jtmiles.beastforgw2.services

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import xyz.jtmiles.beastforgw2.models.Currency

/**
 * Created by JT on 5/21/2016.
 */
interface CurrencyService {
    @GET("currencies/{id}")
    fun getCurrencyById(@Path("id") id: Int): Call<Currency>
}
