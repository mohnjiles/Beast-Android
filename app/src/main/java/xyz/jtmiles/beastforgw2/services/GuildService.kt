package xyz.jtmiles.beastforgw2.services

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Url
import xyz.jtmiles.beastforgw2.models.Guild

interface GuildService {
    @GET
    fun getGuildDetails(@Url guildIdUrl: String): Call<Guild>
}
