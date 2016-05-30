package xyz.jtmiles.beastforgw2.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.boss_timer_layout.view.*
import org.joda.time.DateTimeZone
import org.joda.time.format.DateTimeFormat
import xyz.jtmiles.beastforgw2.R
import xyz.jtmiles.beastforgw2.models.WorldBoss

class BossTimerAdapter(val mBossTimerList: List<WorldBoss?>) : RecyclerView.Adapter<BossTimerAdapter.ViewHolder>() {
    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        val bossTimer = mBossTimerList[position];
        if (bossTimer != null)
            holder?.bindBossTimer(bossTimer)
    }

    override fun getItemCount(): Int = mBossTimerList.size
    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder? =
            ViewHolder(LayoutInflater.from(parent?.context).inflate(R.layout.boss_timer_layout, parent, false))


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindBossTimer(boss: WorldBoss) {
            itemView.tvBossName.text = boss.eventName
            val formatter = DateTimeFormat.forPattern("h:mm a")
            itemView.tvBossStart.text = formatter.print(boss.start?.withZone(DateTimeZone.getDefault()))
        }
    }
}