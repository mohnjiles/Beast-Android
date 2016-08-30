package xyz.jtmiles.beastforgw2.fragments


import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import xyz.jtmiles.beastforgw2.R
import xyz.jtmiles.beastforgw2.activities.CharacterDetailActivity
import xyz.jtmiles.beastforgw2.adapters.CharactersAdapter
import xyz.jtmiles.beastforgw2.managers.CharactersManager
import xyz.jtmiles.beastforgw2.managers.StorageManager
import xyz.jtmiles.beastforgw2.models.Character
import xyz.jtmiles.beastforgw2.models.SavedCharacterList
import xyz.jtmiles.beastforgw2.util.RecyclerItemClickListener
import xyz.jtmiles.beastforgw2.util.bindView
import java.util.*


/**
 * A simple [Fragment] subclass.
 * Use the [CharactersFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class CharactersFragment : Fragment() {


    val rvCharacters: RecyclerView by bindView(R.id.rvCharacters)
    val pbLoading: ProgressBar by bindView(R.id.pbLoading)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val v = inflater!!.inflate(R.layout.fragment_characters, container, false)
        return v
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val actionBar = (activity as AppCompatActivity).supportActionBar
        actionBar?.subtitle = "Characters"

        val storageManager = StorageManager(activity)
        val layoutManager = LinearLayoutManager(activity)
        rvCharacters.layoutManager = layoutManager

        var savedCharacterList: SavedCharacterList? = null
        try {
            savedCharacterList = storageManager.loadCharacters()
        } catch (ex: Exception){
            Log.d("CharactersFragment", ex.message)
        }

        if (savedCharacterList != null && savedCharacterList.characterList.size > 0
                && (Date().time - savedCharacterList.lastUpdated.time) < 15*60*1000) {

            Log.d("CharactersFragment", "Loading from savedCharactersList")

            pbLoading.visibility = View.GONE
            rvCharacters.visibility = View.VISIBLE

            val adapter = CharactersAdapter(activity, savedCharacterList.characterList)

            rvCharacters.adapter = adapter
            rvCharacters.addOnItemTouchListener(RecyclerItemClickListener(activity, RecyclerItemClickListener.OnItemClickListener { view: View, position: Int ->
                val intent = Intent(activity, CharacterDetailActivity::class.java)
                intent.putExtra("character", savedCharacterList!!.characterList[position])
                startActivity(intent)
            }))
        } else {
            Log.d("CharactersFragment", "Loading from web")


            CharactersManager(activity).getAllCharacters(object : Callback<List<Character>> {
                override fun onResponse(call: Call<List<Character>>, response: Response<List<Character>>) {
                    if (response.isSuccessful && activity != null) {

                        pbLoading.visibility = View.GONE
                        rvCharacters.visibility = View.VISIBLE

                        val adapter = CharactersAdapter(activity, response.body())

                        storageManager.saveCharacters(response.body())

                        rvCharacters.adapter = adapter
                        rvCharacters.addOnItemTouchListener(RecyclerItemClickListener(activity, RecyclerItemClickListener.OnItemClickListener { view: View, position: Int ->

                            val intent = Intent(activity, CharacterDetailActivity::class.java)
                            intent.putExtra("character", response.body()[position])
                            startActivity(intent)
                        }))
                    }
                }

                override fun onFailure(call: Call<List<Character>>, t: Throwable) {

                }
            })        }
    }


    companion object {

        fun newInstance(): CharactersFragment {
            return CharactersFragment()
        }
    }


}// Required empty public constructor
