package com.sharifi.kotlinweather.base

import android.support.v7.app.AppCompatActivity

/**
 * Created by sharifi on 10/4/17.
 */
abstract class BaseActivity : AppCompatActivity() {
    var activityStarted = false

    override fun onStart() {
        super.onStart()
        activityStarted = true
    }

    override fun onStop() {
        super.onStop()
        activityStarted = false
    }
}