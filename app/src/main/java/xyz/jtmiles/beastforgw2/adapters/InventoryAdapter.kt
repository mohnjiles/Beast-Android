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
import xyz.jtmiles.beastforgw2.R
import xyz.jtmiles.beastforgw2.models.Inventory
import xyz.jtmiles.beastforgw2.models.Item
import xyz.jtmiles.beastforgw2.services.ItemService
import xyz.jtmiles.beastforgw2.util.Utils


class InventoryAdapter(val mContext: Context, val mInventoryList: List<Inventory>, val mItemList: List<Item>) : RecyclerView.Adapter<InventoryAdapter.ViewHolder>() {


    var itemService: ItemService? = null

    init {
        itemService = Utils.getRetrofit(true).create(ItemService::class.java)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(mContext).inflate(R.layout.inventory_item_layout, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            holder.bindInventory(mInventoryList[position], mItemList[position])
    }


    override fun getItemCount(): Int {
        return mInventoryList.size
    }

    override fun onViewRecycled(holder: ViewHolder?) {
        super.onViewRecycled(holder)
        holder?.itemView?.ivItemIcon?.setImageResource(R.drawable.empty_inventory)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {


        fun bindInventory(inventory: Inventory?,  theItem: Item?) {
            if (inventory == null) {
                itemView.tvCount.text = ""
                itemView.ivItemIcon.setBackgroundColor(Color.parseColor("#00000000"))
                itemView.ivItemIcon.setImageResource(R.drawable.empty_inventory)
                return
            }

            val item = inventory
            if (item.count == null || item.count <= 1)
                itemView.tvCount.text = ""
            else
                itemView.tvCount.text = item.count.toString()

            if (inventory.id == null) {
                itemView.tvCount.text = ""
                itemView.ivItemIcon.setBackgroundColor(Color.parseColor("#00000000"))
                itemView.ivItemIcon.setImageResource(R.drawable.empty_inventory)
                return
            }

            try {
                if (theItem?.icon.isNullOrEmpty()) {
                    itemView.ivItemIcon.setImageResource(R.drawable.empty_inventory)
                } else {
                    Glide.with(itemView.context).load(theItem?.icon)
                            .placeholder(R.drawable.empty_inventory)
                            .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                            .into(itemView.ivItemIcon)
                }
            } catch (ex: IllegalArgumentException) {
                Log.w("InventoryAdapter", ex)
                itemView.ivItemIcon.setImageResource(R.drawable.empty_inventory)
            }

            if (theItem != null) {
                when (theItem.rarity) {
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

