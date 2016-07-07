package xyz.jtmiles.beastforgw2.models

import com.fasterxml.jackson.annotation.JsonProperty

/**
 * Created by JT on 7/5/2016.
 */
data class Buff(@JsonProperty("skill_id") val skillId: Int, val skillDescription: String) {

}