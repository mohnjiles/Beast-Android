package xyz.jtmiles.beastforgw2.fragments


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
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
    var sortedBosses: List<WorldBoss?>? = null

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
                    val theBoss = sortedBosses!![position]!!

                    if (theBoss.start!! < DateTime(DateTimeZone.getDefault())) {
                        Toast.makeText(activity, "Sorry, that boss has already started.", Toast.LENGTH_SHORT).show()
                    } else {

                        val transaction = activity.supportFragmentManager.beginTransaction()
                        val prev = activity.supportFragmentManager.findFragmentByTag("TimerDialogFragment")
                        if (prev != null) {
                            transaction.remove(prev)
                        }
                        transaction.addToBackStack(null)

                        val newFragment = TimerDialogFragment.newInstance(theBoss)
                        newFragment.show(transaction, "TimerDialogFragment")
                    }
                }

        loadWorldBosses()

        timer = fixedRateTimer("BossUpdateTask", false, 0.toLong(), 1000 * 20, { loadWorldBosses() })
    }

    override fun onResume() {
        super.onResume()
        rvBossTimers.addOnItemTouchListener(onTouchListener)
    }

    override fun onPause() {
        super.onPause()
        rvBossTimers.removeOnItemTouchListener(onTouchListener)
    }

    fun loadWorldBosses() {
        async() {
            val sb = StringBuilder()

            try {
                val reader = BufferedReader(InputStreamReader(activity.assets.open("bosses.json")))
                for (line in reader.lines) {
                    sb.append(line)
                }
            } catch (ex: Exception) {
                Log.w("BossTimerFragment", "Error loading bosses: ${ex.message}")
            }

            val mapper = jacksonObjectMapper()

            val json = sb.toString()
            if (mWorldBosses == null) mWorldBosses = ArrayList<WorldBoss>()
            (mWorldBosses as ArrayList<WorldBoss>).clear()
            val worldBossesJsoned: List<WorldBossJson> = mapper.readValue(json)

            for ((start, end, eventName, waypointLink, iconName) in worldBossesJsoned) {
                var bossEndDateTime = DateTime(DateTimeZone.UTC).withTime(end.split(':')[0].toInt(), end.split(':')[1].toInt(), 0, 0)
                var bossStartDateTime = DateTime(DateTimeZone.UTC).withTime(start.split(':')[0].toInt(), start.split(':')[1].toInt(), 0, 0)

                if (bossEndDateTime < DateTime(DateTimeZone.UTC) && end.split(':')[0] != "00") {
                    continue
                }

                if (end.split(':')[0] == "00") {
                    bossEndDateTime = bossEndDateTime.plusDays(1)
                    bossStartDateTime = bossStartDateTime.plusDays(1)
                }

                val actualBoss = WorldBoss()
                actualBoss.start = bossStartDateTime
                actualBoss.end = bossEndDateTime
                actualBoss.eventName = eventName
                actualBoss.waypointLink = waypointLink
                actualBoss.iconName = iconName
                mWorldBosses?.add(actualBoss)
            }

            sortedBosses = (mWorldBosses as ArrayList<WorldBoss>).sortedBy { x -> x.end }.groupBy { it.eventName }.map { it.value.firstOrNull() }

            uiThread {
                pbLoading.visibility = View.GONE
                rvBossTimers.visibility = View.VISIBLE
                rvBossTimers.adapter = BossTimerAdapter(sortedBosses!!)
            }
        }
    }

    companion object {

        fun newInstance(): BossTimerFragment {
            return BossTimerFragment()
        }
    }

}
