package xyz.jtmiles.beastforgw2.models

import com.google.gson.annotations.SerializedName

data class Details(val type: String,
                   val damageType: String,
                   val minPower: Int,
                   val maxPower: Int,
                   val defense: Int,
                   val infusionSlots: List<Any>,
                   @SerializedName("infix_upgrade") val infixUpgrade: InfixUpgrade,
                   val suffixItemId: Int,
                   val secondarySuffixItemId: String)
