package xyz.jtmiles.beastforgw2.models

import java.io.Serializable

data class Attribute(var attribute: String, var modifier: Int) : Serializable {
    companion object {
        private val serialVersionUID = 0L
    }
}

