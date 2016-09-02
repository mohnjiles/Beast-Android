package xyz.jtmiles.beastforgw2.models

import org.joda.time.DateTime

data class Reminder (
        val boss: WorldBoss,
        val time: DateTime
)