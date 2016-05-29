package xyz.jtmiles.beastforgw2.services


import retrofit2.Call
import retrofit2.http.GET

/**
 * Created by JT on 5/15/2016.
 */
interface AchievementsService {
    @GET("achievements")
    fun getAllAchievements(): Call<IntArray>
}
