package xyz.jtmiles.beastforgw2.activities

import android.graphics.Color
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import xyz.jtmiles.beastforgw2.R
import xyz.jtmiles.beastforgw2.models.Inventory
import xyz.jtmiles.beastforgw2.models.Item
import xyz.jtmiles.beastforgw2.services.ItemService
import xyz.jtmiles.beastforgw2.util.Utils
import xyz.jtmiles.beastforgw2.util.bindView
import xyz.jtmiles.beastforgw2.util.bindViews

class ItemDetailActivity : AppCompatActivity() {

    val ivItemIcon: ImageView by bindView(R.id.ivItemIcon)
    val tvItemName: TextView by bindView(R.id.tvItemName)
    val tvWepOrDefenseTitle: TextView by bindView(R.id.tvWepOrDefenseTitle)
    val tvWepOrDefense: TextView by bindView(R.id.tvWepOrDefense)
    val tvStatList: List<TextView> by bindViews(R.id.tvStat1, R.id.tvStat2, R.id.tvStat3, R.id.tvStat4, R.id.tvStat5, R.id.tvStat6, R.id.tvStat7)
    val tvRequiredLevel: TextView by bindView(R.id.tvRequiredLevel)
    val tvDescription: TextView by bindView(R.id.tvDescription)
    val tvType: TextView by bindView(R.id.tvType)

    val toolbar: Toolbar by bindView(R.id.toolbar)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_item_detail)

        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val inventory = intent.extras.getSerializable("item") as Inventory

        val itemService = Utils.getRetrofit(true).create(ItemService::class.java);
        itemService.getItemById(inventory.id).enqueue(object : Callback<Item> {
            override fun onResponse(call: Call<Item>, response: Response<Item>) {
                if (response.isSuccessful) {
                    val item = response.body()

                    Glide.with(this@ItemDetailActivity).load(item.icon).into(ivItemIcon)

                    tvItemName.text = item.name
                    tvDescription.text = item.description?.replace("<c=@flavor>", "")?.replace("</c>", "")
                    tvType.text =
                            if (item.type == "CraftingMaterial")
                                "Crafting Material"
                            else if (item.type == "UpgradeComponent")
                                "Upgrade Component"
                            else if (item.type == "Weapon" || item.type == "Armor")
                                item.details?.type
                            else item.type
                    tvRequiredLevel.text = if (item.level != null && item.level != 0) "Required Level: ${item.level}" else ""
                    supportActionBar?.title = item.name

                    if (item.details?.minPower != null && item.details?.minPower != 0) {
                        tvWepOrDefenseTitle.text = "Weapon Strength:"
                        tvWepOrDefense.text = "${item.details?.minPower} - ${item.details?.maxPower}"
                    } else if (item.details?.defense != null && item.details?.defense != 0) {
                        tvWepOrDefenseTitle.text = "Defense:"
                        tvWepOrDefense.text = item.details?.defense.toString()
                    } else {
                        tvWepOrDefenseTitle.visibility = View.GONE
                        tvWepOrDefense.visibility = View.GONE
                    }

                    when (item.rarity) {
                        "Junk" -> ivItemIcon.setBackgroundColor(Color.parseColor("#a0a1a1"))
                        "Basic" -> ivItemIcon.setBackgroundColor(Color.parseColor("#ffffff"))
                        "Fine" -> ivItemIcon.setBackgroundColor(Color.parseColor("#5191f1"))
                        "Masterwork" -> ivItemIcon.setBackgroundColor(Color.parseColor("#33bb11"))
                        "Rare" -> ivItemIcon.setBackgroundColor(Color.parseColor("#f0d122"))
                        "Exotic" -> ivItemIcon.setBackgroundColor(Color.parseColor("#dd8800"))
                        "Ascended" -> ivItemIcon.setBackgroundColor(Color.parseColor("#ff4488"))
                        "Legendary" -> ivItemIcon.setBackgroundColor(Color.parseColor("#9933ff"))
                        else -> ivItemIcon.setBackgroundColor(Color.parseColor("#ffffff"))
                    }

                    for (i in tvStatList.indices) {
                        val infix = item.details?.infixUpgrade
                        if (infix != null) {
                            val attr = infix.attributes[i]
                            if (attr != null) {
                                tvStatList[i].text = "+${attr.modifier} ${attr.attribute}"
                            }
                        } else {
                            tvStatList[i].visibility = View.GONE
                        }
                    }

                }
            }

            override fun onFailure(call: Call<Item>?, t: Throwable?) {

            }
        })

    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item?.itemId == android.R.id.home) {
            super.onBackPressed()
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}
