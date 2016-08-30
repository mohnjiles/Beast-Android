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
import android.view.View
import android.widget.TextView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import xyz.jtmiles.beastforgw2.R
import xyz.jtmiles.beastforgw2.fragments.*
import xyz.jtmiles.beastforgw2.models.Account
import xyz.jtmiles.beastforgw2.services.AccountService
import xyz.jtmiles.beastforgw2.util.Constants
import xyz.jtmiles.beastforgw2.util.Utils
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
        } else if (Utils.getSharedPrefs(this).getString(Constants.PREF_API_KEY, "") == "") {
            supportFragmentManager.beginTransaction().replace(R.id.container, SettingsFragment.newInstance(), "SettingsFragment").commit()
            mCurrentFragmentTag = "SettingsFragment"
            toolbar.subtitle = "Settings"
        } else {
            supportFragmentManager.beginTransaction().replace(R.id.container, CharactersFragment.newInstance(), "CharactersFragment").commit()
            mCurrentFragmentTag = "CharactersFragment"
            toolbar.subtitle = "Characters"
        }

        setAccountName()

        navigationView.setNavigationItemSelectedListener(this)
    }

    fun setAccountName() {
        val navViewHeader: View = navigationView.getHeaderView(0)
        val tvAccountName: TextView? = navViewHeader.findViewById(R.id.tvAccountName) as TextView?

        val accountService = Utils.getRetrofit(true).create(AccountService::class.java)
        accountService.getAccount(Utils.getApiKeyForAuth(this)).enqueue(object : Callback<Account> {
            override fun onResponse(call: Call<Account>, response: Response<Account>) {
                if (response.isSuccessful) {
                    val account = response.body()
                    tvAccountName?.text = "${account.name}"
                }
            }

            override fun onFailure(call: Call<Account>, t: Throwable) {

            }
        })
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
            supportActionBar?.subtitle = "Boss Timers"
            supportFragmentManager.beginTransaction().replace(R.id.container, BossTimerFragment.newInstance(), "BossTimerFragment").commit()
            mCurrentFragmentTag = "BossTimerFragment"
        } else if (id == R.id.nav_settings) {
            supportActionBar?.subtitle = "Settings"
            supportFragmentManager.beginTransaction().replace(R.id.container, SettingsFragment.newInstance(), "SettingsFragment").commit()
            mCurrentFragmentTag = "SettingsFragment"
        } else if (id == R.id.nav_news) {
            supportActionBar?.subtitle = "News"
            supportFragmentManager.beginTransaction().replace(R.id.container, NewsFragment.newInstance(), "NewsFragment").commit()
            mCurrentFragmentTag = "NewsFragment"
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
