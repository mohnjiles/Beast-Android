package xyz.jtmiles.beastforgw2.fragments


import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import kotlinx.android.synthetic.main.fragment_equipment.*
import org.jetbrains.anko.async
import org.jetbrains.anko.uiThread
import retrofit2.Response
import xyz.jtmiles.beastforgw2.R
import xyz.jtmiles.beastforgw2.activities.ItemDetailActivity
import xyz.jtmiles.beastforgw2.models.Character
import xyz.jtmiles.beastforgw2.models.Inventory
import xyz.jtmiles.beastforgw2.models.Item
import xyz.jtmiles.beastforgw2.services.ItemService
import xyz.jtmiles.beastforgw2.util.Utils

class EquipmentFragment : Fragment() {

    private var mCharacter: Character? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
            mCharacter = arguments.getSerializable("character") as Character
        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? = inflater!!.inflate(R.layout.fragment_equipment, container, false)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val itemService = Utils.getRetrofit(true).create(ItemService::class.java)

        for (equipment in mCharacter!!.equipment) {
            async() {
                val response: Response<Item> = itemService.getItemById(equipment.id!!).execute()
                uiThread {
                    val item = response.body()
                    when (equipment.slot) {
                        "Backpack" -> loadItem(item, ivBackpack)
                        "Coat" -> loadItem(item, ivChest)
                        "Boots" -> loadItem(item, ivBoots)
                        "Gloves" -> loadItem(item, ivGloves)
                        "Helm" -> loadItem(item, ivHelm)
                        "Leggings" -> loadItem(item, ivPants)
                        "Shoulders" -> loadItem(item, ivShoulders)
                        "Accessory1" -> loadItem(item, ivAccessory1)
                        "Accessory2" -> loadItem(item, ivAccessory2)
                        "Ring1" -> loadItem(item, ivRing1)
                        "Ring2" -> loadItem(item, ivRing2)
                        "Amulet" -> loadItem(item, ivAmulet)
                        "WeaponA1" -> loadItem(item, ivWeapon1Slot1)
                        "WeaponA2" -> loadItem(item, ivWeapon1Slot2)
                        "WeaponB1" -> loadItem(item, ivWeapon2Slot1)
                        "WeaponB2" -> loadItem(item, ivWeapon2Slot2)
                    }

                }
            }
        }

    }

    private fun loadItem(item: Item?, imageView: ImageView) {

        if (item == null) {
            imageView.setImageResource(R.drawable.empty_inventory)
            return
        }

        when (item.rarity) {
            "Junk" -> imageView.setBackgroundColor(Color.parseColor("#a0a1a1"))
            "Basic" -> imageView.setBackgroundColor(Color.parseColor("#ffffff"))
            "Fine" -> imageView.setBackgroundColor(Color.parseColor("#5191f1"))
            "Masterwork" -> imageView.setBackgroundColor(Color.parseColor("#33bb11"))
            "Rare" -> imageView.setBackgroundColor(Color.parseColor("#f0d122"))
            "Exotic" -> imageView.setBackgroundColor(Color.parseColor("#dd8800"))
            "Ascended" -> imageView.setBackgroundColor(Color.parseColor("#ff4488"))
            "Legendary" -> imageView.setBackgroundColor(Color.parseColor("#9933ff"))
            else -> imageView.setBackgroundColor(Color.parseColor("#ffffff"))
        }

        Glide.with(this).load(item.icon)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(R.drawable.empty_inventory)
                .into(imageView)

        imageView.setOnClickListener {
            val intent = Intent(activity, ItemDetailActivity::class.java)
            val inventory = Inventory()
            inventory.id = item.id
            inventory.count = 1
            intent.putExtra("item", inventory)
            startActivity(intent)
        }
    }

    companion object {
        fun newInstance(character: Character): EquipmentFragment {
            val fragment = EquipmentFragment()
            val args = Bundle()
            args.putSerializable("character", character)
            fragment.arguments = args
            return fragment
        }
    }

}
