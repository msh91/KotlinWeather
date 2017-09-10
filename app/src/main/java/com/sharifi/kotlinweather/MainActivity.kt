package com.sharifi.kotlinweather

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.sharifi.kotlinweather.home.ForecastsFragment
import org.jetbrains.anko.find

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager
                .beginTransaction()
                .add(R.id.main_container, ForecastsFragment.newInstance(find(R.id.toolbar)), ForecastsFragment.FRAGMENT_NAME)
                .commit()
    }
}
