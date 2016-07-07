package xyz.jtmiles.beastforgw2.adapters

import android.graphics.Color
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import org.joda.time.DateTime
import org.joda.time.Period
import org.joda.time.format.PeriodFormatterBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import xyz.jtmiles.beastforgw2.R
import xyz.jtmiles.beastforgw2.models.Character
import xyz.jtmiles.beastforgw2.models.Guild
import xyz.jtmiles.beastforgw2.services.GuildService
import xyz.jtmiles.beastforgw2.util.Utils
import xyz.jtmiles.beastforgw2.util.bindView
import java.util.*

/**
 * Created by JT on 5/17/2016.
 */
class CharacterInfoAdapter(val mCharacter: Character) : RecyclerView.Adapter<CharacterInfoAdapter.ViewHolder>() {

    var guildService: GuildService? = null

    init {
        guildService = Utils.getRetrofit(false).create(GuildService::class.java)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterInfoAdapter.ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.character_info_layout, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: CharacterInfoAdapter.ViewHolder, position: Int) {
        when (position) {
            0 -> {
                holder.ivInfoImage.setImageResource(R.drawable.skull)
                holder.tvTitle.text = "Deaths"
                holder.tvContent.text = mCharacter.deaths.toString()
                holder.rlLayout.setBackgroundColor(Color.parseColor("#b71c1c"))
            }
            1 -> {
                holder.ivInfoImage.setImageResource(R.drawable.ic_timer_white_48dp_2x)
                holder.tvTitle.text = "Time Played"
                holder.tvContent.text = String.format(Locale.getDefault(), "%d Hours", mCharacter.age!! / 60 / 60)
                holder.rlLayout.setBackgroundColor(Color.parseColor("#311b92"))
            }
            2 -> {
                holder.ivInfoImage.setImageResource(R.drawable.ic_people_white_48dp_2x)
                holder.tvTitle.text = "Guild"
                holder.rlLayout.setBackgroundColor(Color.parseColor("#F9A825"))

                guildService?.getGuildDetails("https://api.guildwars2.com/v1/guild_details.json?guild_id=${mCharacter.guild}")!!.enqueue(object : Callback<Guild> {
                        override fun onResponse(call: Call<Guild>, response: Response<Guild>) {
                        if (response.isSuccessful) {
                            val guild = response.body()
                            holder.tvContent.text = String.format(Locale.getDefault(), "%s [%s]", guild.guildName, guild.tag)

                        }
                    }

                    override fun onFailure(call: Call<Guild>, t: Throwable) {
                        Log.w("CharacterDetailFrag", t.toString())
                    }
                })
            }
            3 -> {
                holder.ivInfoImage.setImageResource(R.drawable.ic_add_white_48dp_2x)
                holder.tvTitle.text = "Created"
                holder.rlLayout.setBackgroundColor(Color.parseColor("#1b5e20"))

                val start = DateTime(mCharacter.created!!)
                val end = DateTime()
                val p = Period(start, end)

                val periodFormatter = PeriodFormatterBuilder()
                        .appendYears().appendSuffix("y").appendSeparator(" ")
                        .appendMonths().appendSuffix("m").appendSeparator(" ")
                        .appendWeeks().appendSuffix("w").appendSeparator(" ")
                        .appendDays().appendSuffix("d").appendSeparator(" ")
                        .toFormatter()

                holder.tvContent.text = p.toString(periodFormatter) + " ago"
            }
        }
    }

    override fun getItemCount(): Int {
        return 4
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val ivInfoImage: ImageView by bindView(R.id.ivInfoImage)
        val tvTitle: TextView by bindView(R.id.tvTitle)
        val tvContent: TextView by bindView(R.id.tvContent)
        val rlLayout: RelativeLayout by bindView(R.id.rlLayout)
    }

}
