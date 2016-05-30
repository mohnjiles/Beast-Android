package xyz.jtmiles.beastforgw2.fragments


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import org.jetbrains.anko.async
import org.jetbrains.anko.uiThread
import org.joda.time.DateTime
import org.joda.time.DateTimeZone
import xyz.jtmiles.beastforgw2.R
import xyz.jtmiles.beastforgw2.adapters.BossTimerAdapter
import xyz.jtmiles.beastforgw2.extensions.lines
import xyz.jtmiles.beastforgw2.models.WorldBoss
import xyz.jtmiles.beastforgw2.models.WorldBossJson
import xyz.jtmiles.beastforgw2.util.bindView
import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*


class BossTimerFragment : Fragment() {

    var mWorldBosses: ArrayList<WorldBoss>? = null
    val rvBossTimers: RecyclerView by bindView(R.id.rvBossTimers)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater!!.inflate(R.layout.fragment_boss_timer, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        rvBossTimers.layoutManager = LinearLayoutManager(activity)
        loadWorldBosses()
    }


    fun loadWorldBosses() {
        async() {
            val sb = StringBuilder()

            try {

                val reader = BufferedReader(InputStreamReader(activity.assets.open("bosses.json")))
                for (line in reader.lines) {
                    sb.append(line)
                }


            } catch (ex: Exception){

            }

            val mapper = jacksonObjectMapper()

            val json = sb.toString()
            if (mWorldBosses == null) mWorldBosses = ArrayList<WorldBoss>()
            val worldBossesJsoned: List<WorldBossJson> = mapper.readValue(json)

            for(boss in worldBossesJsoned) {
                var bossEndDateTime = DateTime(DateTimeZone.UTC).withTime(boss.end.split(':')[0].toInt(), boss.end.split(':')[1].toInt(), 0, 0)
                var bossStartDateTime = DateTime(DateTimeZone.UTC).withTime(boss.start.split(':')[0].toInt(), boss.start.split(':')[1].toInt(), 0, 0)

                if (bossEndDateTime < DateTime(DateTimeZone.UTC)) {
                    bossEndDateTime = bossEndDateTime.plusDays(1)
                    bossStartDateTime = bossStartDateTime.plusDays(1)
                }

                val actualBoss = WorldBoss()
                actualBoss.start = bossStartDateTime
                actualBoss.end = bossEndDateTime
                actualBoss.eventName = boss.eventName
                actualBoss.waypointLink = boss.waypointLink
                (mWorldBosses as ArrayList<WorldBoss>).add(actualBoss)

            }

            val sortedBosses = (mWorldBosses as ArrayList<WorldBoss>).sortedBy { x -> x.start }.groupBy { it.eventName }.map { it.value.firstOrNull() }

            uiThread {
                rvBossTimers.adapter = BossTimerAdapter(sortedBosses)
            }
        }
    }

    companion object {

        fun newInstance(): BossTimerFragment {
            return BossTimerFragment()
        }
    }

}
