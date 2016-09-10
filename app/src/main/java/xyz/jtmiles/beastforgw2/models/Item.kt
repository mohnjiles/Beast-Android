package xyz.jtmiles.beastforgw2.models

import java.io.Serializable
import java.util.*

data class Item (
        val name: String?,
        var description: String?,
        val type: String?,
        val level: Int?,
        val rarity: String?,
        val vendorValue: Int?,
        val defaultSkin: Int?,
        val gameTypes: List<String>,
        val flags: List<String>,
        val restrictions: List<String>,
        val id: Int?,
        val chatLink: String?,
        val icon: String?,
        val details: Details?
        
) : Serializable {



    companion object {
        private val serialVersionUID = 0L
    }

}
