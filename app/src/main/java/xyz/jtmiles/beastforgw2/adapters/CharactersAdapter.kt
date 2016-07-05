package xyz.jtmiles.beastforgw2.adapters

import android.content.Context
import android.graphics.Color
import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import xyz.jtmiles.beastforgw2.R
import xyz.jtmiles.beastforgw2.models.Character
import xyz.jtmiles.beastforgw2.util.Utils
import xyz.jtmiles.beastforgw2.util.bindView
import java.util.*

class CharactersAdapter(private val mContext: Context, private val mCharacterList: List<Character>) : RecyclerView.Adapter<CharactersAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.character_list_layout, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val character = mCharacterList[position]

        holder.ivClassIcon.setImageResource(Utils.getResourceIdByName(mContext, character.profession!!))
        holder.tvCharacterName.text = character.name
        holder.tvCharacterInfo.text = String.format(Locale.getDefault(), "%d %s %s",
                character.level, character.race, character.profession)

        when (character.profession) {
            "Revenant" -> holder.cvCharacter.setCardBackgroundColor(Color.parseColor("#360101"))
            "Guardian" -> holder.cvCharacter.setCardBackgroundColor(Color.parseColor("#264c55"))
            "Engineer" -> holder.cvCharacter.setCardBackgroundColor(Color.parseColor("#79492a"))
            "Necromancer" -> holder.cvCharacter.setCardBackgroundColor(Color.parseColor("#175235"))
            "Elementalist" -> holder.cvCharacter.setCardBackgroundColor(Color.parseColor("#530f14"))
            "Thief" -> holder.cvCharacter.setCardBackgroundColor(Color.parseColor("#533e41"))
            "Warrior" -> holder.cvCharacter.setCardBackgroundColor(Color.parseColor("#5a4b29"))
            "Mesmer" -> holder.cvCharacter.setCardBackgroundColor(Color.parseColor("#2e1933"))
            "Ranger" -> holder.cvCharacter.setCardBackgroundColor(Color.parseColor("#3a4c1d"))
        }
    }

    override fun getItemCount(): Int {
        return mCharacterList.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val ivClassIcon: ImageView by bindView(R.id.ivClassIcon)
        val tvCharacterName: TextView by bindView(R.id.tvCharacterName)
        val tvCharacterInfo: TextView by bindView(R.id.tvCharacterInfo)
        val cvCharacter: CardView by bindView(R.id.cvCharacter)
    }
}
