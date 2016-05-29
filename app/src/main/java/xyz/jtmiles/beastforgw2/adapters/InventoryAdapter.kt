package xyz.jtmiles.beastforgw2.adapters

import android.content.Context
import android.graphics.Color
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import kotlinx.android.synthetic.main.inventory_item_layout.view.*
import org.jetbrains.anko.async
import org.jetbrains.anko.uiThread
import retrofit2.Response
import xyz.jtmiles.beastforgw2.R
import xyz.jtmiles.beastforgw2.models.Inventory
import xyz.jtmiles.beastforgw2.models.Item
import xyz.jtmiles.beastforgw2.services.ItemService
import xyz.jtmiles.beastforgw2.util.Utils


class InventoryAdapter(val mContext: Context, val mItemList: List<Inventory>) : RecyclerView.Adapter<InventoryAdapter.ViewHolder>() {

    var itemService: ItemService? = null

    init {
        itemService = Utils.getRetrofit(true).create(ItemService::class.java)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.inventory_item_layout, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindInventory(mItemList[position], itemService)
    }

    override fun getItemCount(): Int {
        return mItemList.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindInventory(inventory: Inventory?, itemService: ItemService?) {
            if (inventory == null) {
                itemView.tvCount.text = ""
                itemView.ivItemIcon.setImageResource(R.drawable.empty_inventory)
                itemView.ivItemIcon.setBackgroundColor(Color.parseColor("#00000000"))
                return
            }

            val item = inventory
            if (item.count == null || item.count <= 1)
                itemView.tvCount.text = ""
            else
                itemView.tvCount.text = item.count.toString()

            if (inventory.id == null) {
                itemView.tvCount.text = ""
                itemView.ivItemIcon.setImageResource(R.drawable.empty_inventory)
                itemView.ivItemIcon.setBackgroundColor(Color.parseColor("#00000000"))
                return
            }
                async() {
                    val response: Response<Item> = itemService?.getItemById(inventory.id!!)!!.execute()
                    uiThread {
                        if (response.isSuccessful){
                            val item = response.body()

                            try {
                                Glide.with(itemView.context).load(item.icon)
                                        .placeholder(R.drawable.empty_inventory)
                                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                                        .into(itemView.ivItemIcon)
                            } catch (ex: IllegalArgumentException) {
                                Log.w("InventoryAdapter", ex)
                            }

                            when (item.rarity) {
                                "Junk" -> itemView.ivItemIcon.setBackgroundColor(Color.parseColor("#a0a1a1"))
                                "Basic" -> itemView.ivItemIcon.setBackgroundColor(Color.parseColor("#ffffff"))
                                "Fine" -> itemView.ivItemIcon.setBackgroundColor(Color.parseColor("#5191f1"))
                                "Masterwork" -> itemView.ivItemIcon.setBackgroundColor(Color.parseColor("#33bb11"))
                                "Rare" -> itemView.ivItemIcon.setBackgroundColor(Color.parseColor("#f0d122"))
                                "Exotic" -> itemView.ivItemIcon.setBackgroundColor(Color.parseColor("#dd8800"))
                                "Ascended" -> itemView.ivItemIcon.setBackgroundColor(Color.parseColor("#ff4488"))
                                "Legendary" -> itemView.ivItemIcon.setBackgroundColor(Color.parseColor("#9933ff"))
                                else -> itemView.ivItemIcon.setBackgroundColor(Color.parseColor("#ffffff"))
                            }
                        }
                    }
                }
        }

    }
}
