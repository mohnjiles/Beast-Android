package xyz.jtmiles.beastforgw2.services

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import xyz.jtmiles.beastforgw2.models.Item
import xyz.jtmiles.beastforgw2.models.Skin


interface ItemService {
    @GET("items/{id}")
    fun getItemById(@Path("id") itemId: Int): Call<Item>
    @GET("skins/{id}")
    fun getSkinById(@Path("id") skinId: Int?): Call<Skin>
}
