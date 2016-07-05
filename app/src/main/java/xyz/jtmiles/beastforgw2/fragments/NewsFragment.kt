package xyz.jtmiles.beastforgw2.fragments

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import org.jetbrains.anko.async
import org.jetbrains.anko.uiThread
import org.mcsoxford.rss.RSSReader
import xyz.jtmiles.beastforgw2.R
import xyz.jtmiles.beastforgw2.adapters.NewsRecyclerViewAdapter
import xyz.jtmiles.beastforgw2.models.News
import xyz.jtmiles.beastforgw2.util.RecyclerItemClickListener
import xyz.jtmiles.beastforgw2.util.bindView
import java.util.*

class NewsFragment : Fragment() {

    val rvNews: RecyclerView by bindView(R.id.rvNews)
    var mNewsList = ArrayList<News>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater!!.inflate(R.layout.fragment_news, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        loadNews()
    }


    fun loadNews(){
        async() {
            val reader = RSSReader()
            val url = "https://www.guildwars2.com/en/feed/"
            val feed = reader.load(url)

            for (item in feed.items) {
                val news = News(item.title, item.description, item.link.toString(), item.pubDate)
                mNewsList.add(news)
            }

            uiThread {
                val adapter = NewsRecyclerViewAdapter(mNewsList)
                val layoutManager = LinearLayoutManager(activity)
                rvNews.setHasFixedSize(true)
                rvNews.layoutManager = layoutManager
                rvNews.adapter = adapter

                rvNews.addOnItemTouchListener(RecyclerItemClickListener(activity, RecyclerItemClickListener.OnItemClickListener { view: View, position: Int ->
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(mNewsList[position].link))
                    startActivity(intent)
                }))
            }
        }
    }

    companion object {
        fun newInstance(): NewsFragment {
            return NewsFragment()
        }
    }
}
