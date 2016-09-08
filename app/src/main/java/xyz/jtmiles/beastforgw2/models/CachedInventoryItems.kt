package xyz.jtmiles.beastforgw2.models

import java.util.*

data class CachedInventoryItems(
        val itemList: List<Item>,
        val lastUpdated: Date
)