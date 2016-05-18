package xyz.jtmiles.beastforgw2.activities;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import butterknife.BindView;
import butterknife.ButterKnife;
import roboguice.activity.RoboActionBarActivity;
import xyz.jtmiles.beastforgw2.R;
import xyz.jtmiles.beastforgw2.fragments.CharactersFragment;
import xyz.jtmiles.beastforgw2.fragments.SettingsFragment;

public class MainActivity extends RoboActionBarActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.drawer_layout)
    DrawerLayout drawer;
    @BindView(R.id.nav_view)
    NavigationView navigationView;

    ActionBarDrawerToggle toggle;
    public ActionBarDrawerToggle getToggle() {
        return toggle;
    }


    private String mCurrentFragmentTag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);

        toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        if (savedInstanceState != null) {
            String savedFragmentTag = savedInstanceState.getString("fragment_tag");
            Fragment fragment = getSupportFragmentManager().findFragmentByTag(savedFragmentTag);

            if (fragment != null) {
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.container, fragment, savedFragmentTag)
                        .commit();
                mCurrentFragmentTag = savedFragmentTag;
            } else {
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.container, CharactersFragment.newInstance(), "CharactersFragment")
                        .commit();
                mCurrentFragmentTag = "CharactersFragment";
                toolbar.setSubtitle("Characters");
            }
        } else {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.container, CharactersFragment.newInstance(), "CharactersFragment")
                    .commit();
            mCurrentFragmentTag = "CharactersFragment";
            toolbar.setSubtitle("Characters");
        }


        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
        if (toggle != null)
            toggle.setDrawerIndicatorEnabled(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            return false;
        }
        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_characters) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, CharactersFragment.newInstance(), "CharactersFragment")
                    .commit();
            mCurrentFragmentTag = "CharactersFragment";
        } else if (id == R.id.nav_bank) {

        } else if (id == R.id.nav_wallet) {

        } else if (id == R.id.nav_boss_timers) {

        } else if (id == R.id.nav_settings) {
            getSupportActionBar().setSubtitle("Settings");
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, SettingsFragment.newInstance(), "SettingsFragment")
                    .commit();
            mCurrentFragmentTag = "SettingsFragment";
        }

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("fragment_tag", mCurrentFragmentTag);
    }

    public void setmCurrentFragmentTag(String mCurrentFragmentTag) {
        this.mCurrentFragmentTag = mCurrentFragmentTag;
    }


}
