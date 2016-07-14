package xyz.jtmiles.beastforgw2.models


import com.google.gson.annotations.SerializedName

/**
 * Created by JT on 5/21/2016.
 */
data class Account (
    val id: String,
    val name: String,
    val world: Int,
    @SerializedName("commander")
    val isHasCommander: Boolean,
    @SerializedName("guilds")
    val guildIds: List<String>,
    @SerializedName("created")
    val createdDate: String,
    val access: String
)
