package xyz.jtmiles.beastforgw2.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Mini (
        val id: Int,
        val name: String,
        val icon: String,
        val order: Int,
        @SerializedName("item_id") val itemId: Int
) : Serializable {
    companion object {
        private val serialVersionUID = 0L
    }
}