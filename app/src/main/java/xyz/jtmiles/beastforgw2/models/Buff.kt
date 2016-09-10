package xyz.jtmiles.beastforgw2.models

import com.fasterxml.jackson.annotation.JsonProperty
import java.io.Serializable

data class Buff(
        @JsonProperty("skill_id") val skillId: Int,
        val description: String) : Serializable {
    companion object {
        private val serialVersionUID = 0L
    }
}