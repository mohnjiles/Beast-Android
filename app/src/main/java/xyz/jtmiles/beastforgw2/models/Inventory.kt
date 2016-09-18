package xyz.jtmiles.beastforgw2.models

import com.google.gson.annotations.SerializedName

import java.io.Serializable

data class Inventory(

    val id: Int,
    val count: Int,
    val binding: String,
    val upgrades: List<Int>,
    @SerializedName("bound_to") val boundTo: String

) : Serializable {
    companion object {
        private val serialVersionUID = 0L
    }


}
