package xyz.jtmiles.beastforgw2.models

import java.io.Serializable

data class InfusionSlot (val flags: List<String>) : Serializable {

    companion object {
        private val serialVersionUID = 0L
    }
}