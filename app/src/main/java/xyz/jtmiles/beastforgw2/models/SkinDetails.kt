package xyz.jtmiles.beastforgw2.models

import com.google.gson.annotations.SerializedName

data class SkinDetails(
        val type: String,
        @SerializedName("damage_type") val damageType: String,
        @SerializedName("weight_class") val weightClass: String
)