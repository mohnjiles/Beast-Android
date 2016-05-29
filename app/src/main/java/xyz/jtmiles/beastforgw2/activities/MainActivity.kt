package xyz.jtmiles.beastforgw2.activities

import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import xyz.jtmiles.beastforgw2.R
import xyz.jtmiles.beastforgw2.fragments.BankFragment
import xyz.jtmiles.beastforgw2.fragments.CharactersFragment
import xyz.jtmiles.beastforgw2.fragments.SettingsFragment
import xyz.jtmiles.beastforgw2.fragments.WalletFragment
import xyz.jtmiles.beastforgw2.util.bindView

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    val toolbar: Toolbar by bindView(R.id.toolbar)
    val drawer: DrawerLayout by bindView(R.id.drawer_layout)
    val navigationView: NavigationView by bindView(R.id.nav_view)

    var toggle: ActionBarDrawerToggle? = null
        internal set


    private var mCurrentFragmentTag: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(toolbar)

        toggle = ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer.setDrawerListener(toggle)
        toggle!!.syncState()

        if (savedInstanceState != null) {
            val savedFragmentTag = savedInstanceState.getString("fragment_tag")
            val fragment = supportFragmentManager.findFragmentByTag(savedFragmentTag)

            if (fragment != null) {
                supportFragmentManager.beginTransaction().replace(R.id.container, fragment, savedFragmentTag).commit()
                mCurrentFragmentTag = savedFragmentTag
            } else {
                supportFragmentManager.beginTransaction().replace(R.id.container, CharactersFragment.newInstance(), "CharactersFragment").commit()
                mCurrentFragmentTag = "CharactersFragment"
                toolbar.subtitle = "Characters"
            }
        } else {
            supportFragmentManager.beginTransaction().replace(R.id.container, CharactersFragment.newInstance(), "CharactersFragment").commit()
            mCurrentFragmentTag = "CharactersFragment"
            toolbar.subtitle = "Characters"
        }


        navigationView.setNavigationItemSelectedListener(this)
    }

    override fun onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
        if (toggle != null)
            toggle!!.isDrawerIndicatorEnabled = true
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            return false
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        val id = item.itemId

        if (id == R.id.nav_characters) {
            supportFragmentManager.beginTransaction().replace(R.id.container, CharactersFragment.newInstance(), "CharactersFragment").commit()
            mCurrentFragmentTag = "CharactersFragment"
        } else if (id == R.id.nav_bank) {
            supportActionBar?.subtitle = "Bank"
            supportFragmentManager.beginTransaction().replace(R.id.container, BankFragment.newInstance(), "BankFragment").commit()
            mCurrentFragmentTag = "BankFragment"
        } else if (id == R.id.nav_wallet) {
            supportActionBar?.subtitle = "Wallet"
            supportFragmentManager.beginTransaction().replace(R.id.container, WalletFragment.newInstance(), "WalletFragment").commit()
            mCurrentFragmentTag = "WalletFragment"
        } else if (id == R.id.nav_boss_timers) {

        } else if (id == R.id.nav_settings) {
            supportActionBar?.subtitle = "Settings"
            supportFragmentManager.beginTransaction().replace(R.id.container, SettingsFragment.newInstance(), "SettingsFragment").commit()
            mCurrentFragmentTag = "SettingsFragment"
        }

        drawer.closeDrawer(GravityCompat.START)
        return true
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString("fragment_tag", mCurrentFragmentTag)
    }

    fun setmCurrentFragmentTag(mCurrentFragmentTag: String) {
        this.mCurrentFragmentTag = mCurrentFragmentTag
    }


}
