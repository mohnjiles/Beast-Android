package xyz.jtmiles.beastforgw2.models


data class Skin (
        val name: String,
        val type: String,
        val flags: List<String>,
        val restrictions: List<String>,
        val icon: String,
        val rarity: String,
        val description: String,
        val details: SkinDetails

)