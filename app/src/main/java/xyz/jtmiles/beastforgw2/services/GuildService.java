package xyz.jtmiles.beastforgw2.services;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;
import xyz.jtmiles.beastforgw2.models.Guild;

/**
 * Created by JT on 5/16/2016.
 */
public interface GuildService {
    @GET
    Call<Guild> getGuildDetails(@Url String guildIdUrl);
}
