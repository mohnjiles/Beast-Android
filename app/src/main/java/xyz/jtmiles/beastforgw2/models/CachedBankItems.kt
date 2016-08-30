package xyz.jtmiles.beastforgw2.models

import java.util.*

data class CachedBankItems(
        val bankItems: List<Inventory>,
        val lastUpdated: Date
)