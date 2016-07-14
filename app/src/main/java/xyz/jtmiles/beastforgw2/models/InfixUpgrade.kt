package xyz.jtmiles.beastforgw2.models

import java.io.Serializable

data class InfixUpgrade(val id: Int, val buff: Buff, val attributes: List<Attribute>) : Serializable {
    companion object {
        private val serialVersionUID = 0L
    }
}
