package com.sharifi.kotlinweather.data.repository

import android.util.Log
import java.net.URL

/**
 * Created by sharifi on 7/20/17.
 */

class Request(val url: String) {
    fun run() {
        val foreCastJsonStr = URL(url).readText()
        Log.d(javaClass.simpleName, foreCastJsonStr)
    }
}

