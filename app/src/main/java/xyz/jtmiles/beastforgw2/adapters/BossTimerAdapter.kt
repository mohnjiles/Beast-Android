package xyz.jtmiles.beastforgw2.adapters

import android.graphics.Color
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.boss_timer_layout.view.*
import org.joda.time.DateTime
import org.joda.time.DateTimeZone
import org.joda.time.Period
import org.joda.time.format.DateTimeFormat
import org.joda.time.format.PeriodFormatterBuilder
import xyz.jtmiles.beastforgw2.R
import xyz.jtmiles.beastforgw2.models.WorldBoss

class BossTimerAdapter(val mBossTimerList: List<WorldBoss?>) : RecyclerView.Adapter<BossTimerAdapter.ViewHolder>() {
    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        val bossTimer = mBossTimerList[position];
        if (bossTimer != null)
            holder?.bindBossTimer(bossTimer, position)
    }

    override fun getItemCount(): Int = mBossTimerList.size
    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder? =
            ViewHolder(LayoutInflater.from(parent?.context).inflate(R.layout.boss_timer_layout, parent, false))


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindBossTimer(boss: WorldBoss, pos: Int) {
            itemView.tvBossName.text = boss.eventName
            val formatter = DateTimeFormat.forPattern("h:mm a")
            //val t = PrettyTime()
            //val timeFormatted = t.format(boss.start?.toDate())
            //itemView.tvBossStart.text = timeFormatted

            val start = boss.start
            val end = DateTime()
            val p = Period(start, end)
            val periodFormatter = PeriodFormatterBuilder()
                .appendHours()
            .appendSuffix("h").appendSeparator(" ")
            .appendMinutes().appendSuffix("m").toFormatter()

            val timeString = p.toString(periodFormatter)

            if (timeString.contains("-")) {
                itemView.tvBossStart.text = timeString.replace("-", "")
            } else if (timeString.isNullOrBlank()) {
                itemView.tvBossStart.text = "Starting now!"
            }
            else {
                itemView.tvBossStart.text = "Started $timeString ago"
            }
            itemView.tvBossStartActual.text = formatter.print(boss.start?.withZone(DateTimeZone.getDefault()))

            if (pos < 2)
                itemView.tvBossName.setTextColor(Color.parseColor("#F5A320"))
            else
                itemView.tvBossName.setTextColor(Color.parseColor("#FFFFFF"))
        }
    }
}