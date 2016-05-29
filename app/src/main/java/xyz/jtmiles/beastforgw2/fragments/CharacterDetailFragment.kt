package xyz.jtmiles.beastforgw2.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import xyz.jtmiles.beastforgw2.R
import xyz.jtmiles.beastforgw2.VerticalSpaceItemDecoration
import xyz.jtmiles.beastforgw2.adapters.CharacterInfoAdapter
import xyz.jtmiles.beastforgw2.models.Character
import xyz.jtmiles.beastforgw2.util.Utils
import xyz.jtmiles.beastforgw2.util.bindView
import java.util.*

class CharacterDetailFragment : Fragment() {

    val tvCharacterName: TextView by bindView(R.id.tvCharacterName)
    val tvCharacterRaceClass: TextView by bindView(R.id.tvCharacterRaceClass)
    val rvStats: RecyclerView by bindView(R.id.rvStats)

    val ivClassIcon: ImageView by bindView(R.id.ivClassIcon)


    private var mCharacter: Character? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
            mCharacter = arguments.getSerializable("character") as Character
        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? = inflater!!.inflate(R.layout.fragment_character_detail, container, false)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val layoutManager = LinearLayoutManager(activity)
        rvStats.layoutManager = layoutManager


        tvCharacterName.text = mCharacter?.name
        tvCharacterRaceClass.text = String.format(Locale.getDefault(), "%d %s %s", mCharacter?.level, mCharacter?.race, mCharacter?.profession)
        ivClassIcon.setImageResource(Utils.getResourceIdByName(activity, mCharacter?.profession!!))

        rvStats.addItemDecoration(VerticalSpaceItemDecoration(32))
        rvStats.adapter = CharacterInfoAdapter(mCharacter!!)
    }

    companion object {

        fun newInstance(character: Character): CharacterDetailFragment {
            val fragment = CharacterDetailFragment()
            val args = Bundle()
            args.putSerializable("character", character)
            fragment.arguments = args
            return fragment
        }
    }
}// Required empty public constructor
