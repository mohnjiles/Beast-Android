package xyz.jtmiles.beastforgw2.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Details(val type: String,
                   val damageType: String,
                   @SerializedName("min_power") val minPower: Int,
                   @SerializedName("max_power") val maxPower: Int,
                   val defense: Int,
                   val infusionSlots: List<InfusionSlot>,
                   @SerializedName("infix_upgrade") val infixUpgrade: InfixUpgrade,
                   val suffixItemId: Int,
                   val secondarySuffixItemId: String) : Serializable{
    companion object {
        private val serialVersionUID = 0L
    }
}
