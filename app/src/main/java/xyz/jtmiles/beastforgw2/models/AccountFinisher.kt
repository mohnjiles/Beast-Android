package xyz.jtmiles.beastforgw2.models

import java.io.Serializable

data class AccountFinisher (
        val id: Int,
        val permanent: Boolean,
        val quantity: Int
) : Serializable {
    companion object {
        private val serialVersionUID = 0L
    }
}