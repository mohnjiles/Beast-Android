package xyz.jtmiles.beastforgw2.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import kotlinx.android.synthetic.main.minis_list_layout.view.*
import xyz.jtmiles.beastforgw2.R
import xyz.jtmiles.beastforgw2.models.Mini


class MinisAdapter(val minisList: List<Mini>) : RecyclerView.Adapter<MinisAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.minis_list_layout, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindTitles(minisList[position])
    }

    override fun getItemCount(): Int {
        return minisList.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindTitles(mini: Mini){
            itemView.tvMiniName.text = mini.name
            Glide.with(itemView.context)
                .load(mini.icon)
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .into(itemView.ivMiniIcon)
        }
    }
}