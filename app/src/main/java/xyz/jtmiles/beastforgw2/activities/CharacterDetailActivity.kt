package xyz.jtmiles.beastforgw2.activities

import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import xyz.jtmiles.beastforgw2.R
import xyz.jtmiles.beastforgw2.fragments.CharacterDetailFragment
import xyz.jtmiles.beastforgw2.fragments.EquipmentFragment
import xyz.jtmiles.beastforgw2.fragments.InventoryFragment
import xyz.jtmiles.beastforgw2.fragments.SettingsFragment
import xyz.jtmiles.beastforgw2.models.Character
import xyz.jtmiles.beastforgw2.util.bindView

class CharacterDetailActivity : AppCompatActivity() {

    private var mSectionsPagerAdapter: SectionsPagerAdapter? = null

    val toolbar: Toolbar by bindView(R.id.toolbar)
    val mViewPager: ViewPager by bindView(R.id.container)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_character_detail)

        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = SectionsPagerAdapter(supportFragmentManager)

        // Set up the ViewPager with the sections adapter.
        mViewPager.adapter = mSectionsPagerAdapter
        mViewPager.offscreenPageLimit = 2

        val tabLayout = findViewById(R.id.tabs) as TabLayout?
        tabLayout!!.setupWithViewPager(mViewPager)

        val intent = intent
        var mCharacter = intent.extras?.getSerializable("character")
        if (mCharacter != null) {
            mCharacter = mCharacter as Character
            supportActionBar?.title = "Beast for GW2"
            supportActionBar?.subtitle = mCharacter.name
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_character_detail, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        val id = item.itemId

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true
        }

        return super.onOptionsItemSelected(item)
    }


    /**
     * A [FragmentPagerAdapter] that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    inner class SectionsPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

        override fun getItem(position: Int): Fragment {
            val mCharacter = intent.extras.getSerializable("character") as Character

            when (position) {
                0 -> return CharacterDetailFragment.newInstance(mCharacter)
                1 -> return InventoryFragment.newInstance(mCharacter)
                2 -> return EquipmentFragment.newInstance(mCharacter)
            }

            return SettingsFragment.newInstance()
        }

        override fun getCount(): Int {
            // Show 3 total pages.
            return 3
        }

        override fun getPageTitle(position: Int): CharSequence? {
            when (position) {
                0 -> return "Details"
                1 -> return "Inventory"
                2 -> return "Equipment"
            }
            return null
        }
    }
}
