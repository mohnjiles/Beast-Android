package xyz.jtmiles.beastforgw2.fragments


import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.text.method.LinkMovementMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import xyz.jtmiles.beastforgw2.R
import xyz.jtmiles.beastforgw2.util.Constants
import xyz.jtmiles.beastforgw2.util.Utils
import xyz.jtmiles.beastforgw2.util.bindView


/**
 * A simple [Fragment] subclass.
 * Use the [SettingsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SettingsFragment : Fragment() {

    val tvApiKeyHelp: TextView by bindView(R.id.tvApiKeyHelp)
    val etApiKey: EditText by bindView(R.id.etApiKey)
    val btnSaveApiKey: Button by bindView(R.id.btnSaveApiKey)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val v = inflater!!.inflate(R.layout.fragment_settings, container, false)
        return v
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        tvApiKeyHelp.movementMethod = LinkMovementMethod.getInstance()

        btnSaveApiKey.setOnClickListener {
            Utils.getSharedPrefs(activity).edit().putString(Constants.PREF_API_KEY, etApiKey.text.toString()).commit()
            Snackbar.make(tvApiKeyHelp, "API Key saved successfully.", Snackbar.LENGTH_LONG).show()
        }
    }

    companion object {

        fun newInstance(): SettingsFragment {
            return SettingsFragment()
        }
    }
}// Required empty public constructor
