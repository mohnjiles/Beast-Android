package xyz.jtmiles.beastforgw2.models

import java.util.*

data class CachedBankItems(
        val bankItems: List<Item>,
        val lastUpdated: Date
)