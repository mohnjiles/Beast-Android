package xyz.jtmiles.beastforgw2.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Finisher(
        val id: Int,
        @SerializedName("unlock_details") val unlockDetails: String,
        val order: Int,
        val icon: String,
        val name: String
) : Serializable {
    companion object {
        private val serialVersionUID = 0L
    }
}