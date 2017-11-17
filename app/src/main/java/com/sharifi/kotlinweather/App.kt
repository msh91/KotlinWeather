package com.sharifi.kotlinweather

import android.app.Application

/**
 * Created by sharifi on 8/4/17.
 */
class App : Application() {
    companion object {
        lateinit var instance: App
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }

}