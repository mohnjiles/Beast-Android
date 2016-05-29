package xyz.jtmiles.beastforgw2.models

import java.io.Serializable
import java.util.*

class Character : Serializable {

    var name: String? = null

    var race: String? = null

    var gender: String? = null

    var profession: String? = null

    var level: Int? = null

    var guild: String? = null

    var age: Int? = null

    var created: String? = null

    var deaths: Int? = null

    var crafting: List<Crafting> = ArrayList()

    var specializations: Specializations? = null

    var skills: Skills? = null

    var equipment: List<Equipment> = ArrayList()

    var recipes: List<Int> = ArrayList()

    var equipmentPvp: EquipmentPvp? = null

    var bags: List<Bag> = ArrayList()

    companion object {
        private val serialVersionUID = 0L
    }


}
