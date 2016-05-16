package xyz.jtmiles.beastforgw2.services;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;
import retrofit2.http.Query;
import xyz.jtmiles.beastforgw2.models.Character;

/**
 * Created by JT on 5/15/2016.
 */
public interface CharactersService {
    @GET("characters")
    Call<List<String>> getCharacterNames(@Header("Authorization") String authorization);

    @GET("characters/{name}")
    Call<Character> getCharacterByName(@Path("name") String characterName, @Header("Authorization") String authorization);

    @GET("characters")
    Call<List<Character>> getCharactersByPage(@Query("page") int page, @Header("Authorization") String authorization);

}
