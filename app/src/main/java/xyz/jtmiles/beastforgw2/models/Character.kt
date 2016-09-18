package xyz.jtmiles.beastforgw2.models

import java.io.Serializable
import java.util.*

data class Character (

    val name: String,
    val race: String,
    val gender: String,
    val profession: String,
    val level: Int,
    val guild: String,
    val age: Int,
    val created: String,
    val deaths: Int,
    val crafting: List<Crafting>,
    val specializations: Specializations,
    val skills: Skills,
    val equipment: List<Equipment>,
    val recipes: List<Int>,
    val equipmentPvp: EquipmentPvp,
    val bags: List<Bag>
): Serializable {

    companion object {
        private val serialVersionUID = 0L
    }


}
