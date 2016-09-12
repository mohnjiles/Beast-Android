package xyz.jtmiles.beastforgw2.models

import java.io.Serializable

data class Title (
        val id: Int,
        val name: String,
        val achievement: Int
) : Serializable {
    companion object {
        private val serialVersionUID = 0L
    }
}