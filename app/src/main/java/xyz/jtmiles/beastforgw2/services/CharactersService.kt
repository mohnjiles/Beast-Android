package xyz.jtmiles.beastforgw2.services

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query
import xyz.jtmiles.beastforgw2.models.Character

interface CharactersService {
    @GET("characters")
    fun getCharacterNames(@Header("Authorization") authorization: String): Call<List<String>>

    @GET("characters/{name}")
    fun getCharacterByName(@Path("name") characterName: String, @Header("Authorization") authorization: String): Call<Character>

    @GET("characters")
    fun getCharactersByPage(@Query("page") page: Int, @Header("Authorization") authorization: String): Call<List<Character>>

}
