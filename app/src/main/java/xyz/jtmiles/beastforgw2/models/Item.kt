package xyz.jtmiles.beastforgw2.models

import java.util.*

class Item {

    var name: String? = null
    var description: String? = null
    var type: String? = null
    var level: Int? = null
    var rarity: String? = null
    var vendorValue: Int? = null
    var defaultSkin: Int? = null
    var gameTypes: List<String> = ArrayList()
    var flags: List<String> = ArrayList()
    var restrictions: List<Any> = ArrayList()
    var id: Int? = null
    var chatLink: String? = null
    var icon: String? = null
    var details: Details? = null

}
