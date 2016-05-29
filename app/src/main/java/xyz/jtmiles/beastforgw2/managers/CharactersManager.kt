package xyz.jtmiles.beastforgw2.managers

import android.content.Context
import retrofit2.Callback
import xyz.jtmiles.beastforgw2.models.Character
import xyz.jtmiles.beastforgw2.services.CharactersService
import xyz.jtmiles.beastforgw2.util.Utils

class CharactersManager(private val mContext: Context) {

    private val charactersService: CharactersService

    init {
        charactersService = Utils.getRetrofit(true).create(CharactersService::class.java)
    }

    fun getCharacterByName(name: String, callback: Callback<Character>) {
        val characterCall = charactersService.getCharacterByName(name, Utils.getApiKeyForAuth(mContext))
        characterCall.enqueue(callback)
    }

    fun getAllCharacters(callback: Callback<List<Character>>) {
        val allCharactersCall = charactersService.getCharactersByPage(0, Utils.getApiKeyForAuth(mContext))
        allCharactersCall.enqueue(callback)
    }
}
