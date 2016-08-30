package xyz.jtmiles.beastforgw2.fragments


import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import com.nikhilpanju.recyclerviewenhanced.RecyclerTouchListener
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
import kotlin.concurrent.fixedRateTimer


class BossTimerFragment : Fragment() {

    var mWorldBosses: ArrayList<WorldBoss>? = null
    val rvBossTimers: RecyclerView by bindView(R.id.rvBossTimers)
    val pbLoading: ProgressBar by bindView(R.id.pbLoading)
    var onTouchListener: RecyclerTouchListener? = null
    var timer: Timer? = null

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

        onTouchListener = RecyclerTouchListener(activity, rvBossTimers)
        onTouchListener!!.setSwipeOptionViews(R.id.rlAdd)
                .setSwipeable(R.id.rlForeground, R.id.rowBG)
                    { viewID, position ->
                        Snackbar.make(rvBossTimers, "It worked! $position", Snackbar.LENGTH_SHORT).show()
                    }

        loadWorldBosses()

        timer = fixedRateTimer("BossUpdateTask", false, 0.toLong(), 1000 * 20, {loadWorldBosses()})
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
                Log.w("BossTimerFragment", "Error loading bosses: ${ex.message}")
            }

            val mapper = jacksonObjectMapper()

            val json = sb.toString()
            if (mWorldBosses == null) mWorldBosses = ArrayList<WorldBoss>()
            (mWorldBosses as ArrayList<WorldBoss>).clear()
            val worldBossesJsoned: List<WorldBossJson> = mapper.readValue(json)

            for (boss in worldBossesJsoned) {
                val bossEndDateTime = DateTime(DateTimeZone.UTC).withTime(boss.end.split(':')[0].toInt(), boss.end.split(':')[1].toInt(), 0, 0)
                val bossStartDateTime = DateTime(DateTimeZone.UTC).withTime(boss.start.split(':')[0].toInt(), boss.start.split(':')[1].toInt(), 0, 0)

                if (bossEndDateTime < DateTime(DateTimeZone.UTC)) {
                    continue
                }

                val actualBoss = WorldBoss()
                actualBoss.start = bossStartDateTime
                actualBoss.end = bossEndDateTime
                actualBoss.eventName = boss.eventName
                actualBoss.waypointLink = boss.waypointLink
                actualBoss.iconName = boss.iconName
                mWorldBosses?.add(actualBoss)
            }

            val sortedBosses = (mWorldBosses as ArrayList<WorldBoss>).sortedBy { x -> x.end }.groupBy { it.eventName }.map { it.value.firstOrNull() }

            uiThread {
                pbLoading.visibility = View.GONE
                rvBossTimers.visibility = View.VISIBLE
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
