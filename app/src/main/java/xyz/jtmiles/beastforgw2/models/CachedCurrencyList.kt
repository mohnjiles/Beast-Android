package xyz.jtmiles.beastforgw2.models

import java.util.*

data class CachedCurrencyList (
        val currencyList: List<Currency>,
        val lastUpdated: Date
)