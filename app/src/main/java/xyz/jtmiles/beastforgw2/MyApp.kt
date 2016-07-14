package xyz.jtmiles.beastforgw2

import android.app.Application

import net.danlew.android.joda.JodaTimeAndroid

class MyApp : Application() {
    override fun onCreate() {
        super.onCreate()
        JodaTimeAndroid.init(this)
    }
}