package xyz.jtmiles.beastforgw2.models

import java.util.*

data class CachedWalletList(
        val wallet: List<Wallet>,
        val lastUpdated: Date
)