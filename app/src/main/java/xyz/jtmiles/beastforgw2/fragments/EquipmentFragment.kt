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
import xyz.jtmiles.beastforgw2.models.Equipment
import xyz.jtmiles.beastforgw2.models.Item
import xyz.jtmiles.beastforgw2.models.Skin
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
                val itemResponse: Response<Item> = itemService.getItemById(equipment.id!!).execute()
                var skinResponse: Response<Skin>? = null
                try {
                    skinResponse = itemService.getSkinById(equipment.skin).execute()
                } catch (ex: Exception){
                    // ignored
                }

                uiThread {
                    val item = itemResponse.body()
                    val skin = skinResponse?.body()
                    when (equipment.slot) {
                        "Backpack" -> loadItem(item, skin, equipment, ivBackpack)
                        "Coat" -> loadItem(item, skin, equipment, ivChest)
                        "Boots" -> loadItem(item, skin, equipment, ivBoots)
                        "Gloves" -> loadItem(item, skin, equipment, ivGloves)
                        "Helm" -> loadItem(item, skin, equipment, ivHelm)
                        "Leggings" -> loadItem(item, skin, equipment, ivPants)
                        "Shoulders" -> loadItem(item, skin, equipment, ivShoulders)
                        "Accessory1" -> loadItem(item, skin, equipment, ivAccessory1)
                        "Accessory2" -> loadItem(item, skin, equipment, ivAccessory2)
                        "Ring1" -> loadItem(item, skin, equipment, ivRing1)
                        "Ring2" -> loadItem(item, skin, equipment, ivRing2)
                        "Amulet" -> loadItem(item, skin, equipment, ivAmulet)
                        "WeaponA1" -> loadItem(item, skin, equipment, ivWeapon1Slot1)
                        "WeaponA2" -> loadItem(item, skin, equipment, ivWeapon1Slot2)
                        "WeaponB1" -> loadItem(item, skin, equipment, ivWeapon2Slot1)
                        "WeaponB2" -> loadItem(item, skin, equipment, ivWeapon2Slot2)
                    }

                }
            }
        }

    }

    private fun loadItem(item: Item?, skin: Skin?, equipment: Equipment, imageView: ImageView?) {

        if(imageView == null) return

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

        val icon = if (skin?.icon.isNullOrEmpty()) item.icon else skin?.icon

        Glide.with(this).load(icon)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(R.drawable.empty_inventory)
                .into(imageView)

        imageView.setOnClickListener {
            val intent = Intent(activity, ItemDetailActivity::class.java)
            item.description = item.description?.replace("<c=@flavor>", "")?.replace("</c>", "")?.replace("<br>", "")
            intent.putExtra("item", item)
            intent.putExtra("equipment", equipment)
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
