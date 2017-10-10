package com.sharifi.kotlinweather

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.sharifi.kotlinweather.home.HomeFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager
                .beginTransaction()
                .add(R.id.main_container, HomeFragment(), "HomeFragment")
                .commit()
    }
}
