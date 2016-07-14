package xyz.jtmiles.beastforgw2.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Details(val type: String,
                   val damageType: String,
                   val minPower: Int,
                   val maxPower: Int,
                   val defense: Int,
                   val infusionSlots: List<Any>,
                   @SerializedName("infix_upgrade") val infixUpgrade: InfixUpgrade,
                   val suffixItemId: Int,
                   val secondarySuffixItemId: String) : Serializable{
    companion object {
        private val serialVersionUID = 0L
    }
}
