package xyz.jtmiles.beastforgw2.managers;

import android.app.Activity;

import com.google.inject.Inject;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import roboguice.inject.ContextSingleton;
import xyz.jtmiles.beastforgw2.models.Character;
import xyz.jtmiles.beastforgw2.services.CharactersService;
import xyz.jtmiles.beastforgw2.util.Utils;

/**
 * Created by JT on 5/15/2016.
 */

@ContextSingleton
public class CharactersManager {

    @Inject
    Activity activity;

    private CharactersService charactersService;

    public CharactersManager() {
        charactersService = Utils.getRetrofit().create(CharactersService.class);
    }

    public void getCharacterByName(String name, Callback<Character> callback) {
        final Call<Character> characterCall = charactersService.getCharacterByName(name, Utils.getApiKey(activity));
        characterCall.enqueue(callback);
    }

    public void getAllCharacters(Callback<List<Character>> callback) {
        final Call<List<Character>> allCharactersCall = charactersService.getCharactersByPage(0, Utils.getApiKey(activity));
        allCharactersCall.enqueue(callback);
    }
}
