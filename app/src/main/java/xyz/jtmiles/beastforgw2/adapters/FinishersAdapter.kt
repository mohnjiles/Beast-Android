package xyz.jtmiles.beastforgw2.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import kotlinx.android.synthetic.main.finishers_list_layout.view.*
import xyz.jtmiles.beastforgw2.R
import xyz.jtmiles.beastforgw2.models.Finisher


class FinishersAdapter(val finishersList: List<Finisher>) : RecyclerView.Adapter<FinishersAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.finishers_list_layout, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindTitles(finishersList[position])
    }

    override fun getItemCount(): Int {
        return finishersList.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {


        fun bindTitles(finisher: Finisher){
            itemView.tvFinisherName.text = finisher.name
            itemView.tvUnlockDetails.text = finisher.unlockDetails.replace("<c=@reminder>", "").replace("</c>", "")
            Glide.with(itemView.context).load(finisher.icon)
                    .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                    .into(itemView.ivFinisherIcon)
        }
    }
}