package xyz.jtmiles.beastforgw2.models

import java.io.Serializable
import java.util.*

class Equipment : Serializable {


    var id: Int? = null
    var slot: String? = null
    var infusions: List<Int> = ArrayList()
    var upgrades: List<Int> = ArrayList()
    var skin: Int? = null

    companion object {
        private val serialVersionUID = 0L
    }

}
