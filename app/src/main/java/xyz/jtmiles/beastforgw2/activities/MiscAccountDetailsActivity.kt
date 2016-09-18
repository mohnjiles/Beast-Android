package xyz.jtmiles.beastforgw2.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.view.ViewPager
import android.support.v7.widget.Toolbar
import android.view.MenuItem
import xyz.jtmiles.beastforgw2.R
import xyz.jtmiles.beastforgw2.fragments.FinisherFragment
import xyz.jtmiles.beastforgw2.fragments.MinisFragment
import xyz.jtmiles.beastforgw2.fragments.SettingsFragment
import xyz.jtmiles.beastforgw2.fragments.TitlesFragment
import xyz.jtmiles.beastforgw2.util.bindView

class MiscAccountDetailsActivity : AppCompatActivity() {

    val toolbar: Toolbar by bindView(R.id.toolbar)
    val mViewPager: ViewPager by bindView(R.id.container)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_misc_account_details)

        setSupportActionBar(toolbar)
        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        toolbar.title = "Beast for GW2"
        toolbar.subtitle = "Misc Account Details"

        val mSectionsPagerAdapter = SectionsPagerAdapter(supportFragmentManager)
        mViewPager.adapter = mSectionsPagerAdapter
        mViewPager.offscreenPageLimit = 2

        val tabLayout = findViewById(R.id.tabs) as TabLayout?
        tabLayout!!.setupWithViewPager(mViewPager)
    }


    override fun onOptionsItemSelected(item: MenuItem?): Boolean {

        when (item?.itemId) {
            android.R.id.home -> {
                onBackPressed()
                return true
            }
        }

        return super.onOptionsItemSelected(item)
    }


    inner class SectionsPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

        override fun getItem(position: Int): Fragment {

            when (position) {
                0 -> return TitlesFragment.newInstance()
                1 -> return FinisherFragment.newInstance()
                2 -> return MinisFragment.newInstance()
            }

            return SettingsFragment.newInstance()
        }

        override fun getCount(): Int {
            // Show 3 total pages.
            return 3
        }

        override fun getPageTitle(position: Int): CharSequence? {
            when (position) {
                0 -> return "Titles"
                1 -> return "Finishers"
                2 -> return "Minis"
            }
            return null
        }
    }
}
