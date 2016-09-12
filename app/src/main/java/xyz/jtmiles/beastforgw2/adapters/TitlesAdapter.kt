package xyz.jtmiles.beastforgw2.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.titles_list_layout.view.*
import xyz.jtmiles.beastforgw2.R

class TitlesAdapter(val titlesList: List<String>) : RecyclerView.Adapter<TitlesAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.titles_list_layout, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindTitles(titlesList[position])
    }

    override fun getItemCount(): Int {
        return titlesList.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {


        fun bindTitles(title: String){
            itemView.tvTitle.text = title
        }
    }
}