package com.sharifi.kotlinweather

import android.app.Application
import com.sharifi.kotlinweather.util.CustomDelegate

/**
 * Created by sharifi on 8/4/17.
 */
class App : Application() {
    companion object {
        var instance: App by CustomDelegate.notNullSingleValue()
        fun instance() = instance
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }

}