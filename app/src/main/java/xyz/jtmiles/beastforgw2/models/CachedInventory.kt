package xyz.jtmiles.beastforgw2.models

import java.util.*

data class CachedInventory(
        val inventory: List<Inventory>,
        val lastUpdated: Date
)