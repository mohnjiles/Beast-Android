package xyz.jtmiles.beastforgw2.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_news_list.view.*
import xyz.jtmiles.beastforgw2.R
import xyz.jtmiles.beastforgw2.models.News

class NewsRecyclerViewAdapter(private val mNewsList: List<News>) : RecyclerView.Adapter<NewsRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.fragment_news_list, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindNews(mNewsList[position])
    }

    override fun getItemCount(): Int {
        return mNewsList.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {


        fun bindNews(news: News){
            itemView.tvNewsTitle.text = news.title
            itemView.tvNewsDescription.text = news.description.replace(Regex("<[^>]*>"), "").replace("Read More", "")
                .replace(Regex("(&#\\d+;)"), " ")

            itemView.tvPubDate.text = "Published ${news.pubDate}"
        }
    }
}
