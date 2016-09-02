package xyz.jtmiles.beastforgw2.models

import org.joda.time.DateTime
import java.io.Serializable

/**
 * Created by JT on 5/29/2016.
 */

class WorldBoss : Serializable {
    var start: DateTime? = null
    var end: DateTime? = null
    var eventName: String? = null
    var waypointLink: String? = null
    var iconName: String? = null

    companion object {
        private val serialVersionUID = 0L
    }
}