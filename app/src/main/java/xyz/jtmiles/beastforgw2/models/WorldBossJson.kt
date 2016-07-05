package xyz.jtmiles.beastforgw2.models

import com.fasterxml.jackson.annotation.JsonProperty

/**
 * Created by JT on 5/29/2016.
 */

data class WorldBossJson(val start: String,
                         val end: String,
                         @JsonProperty("event_name") val eventName: String,
                         @JsonProperty("waypoint_link") val waypointLink: String,
                         @JsonProperty("icon_name") val iconName: String)
{
}
