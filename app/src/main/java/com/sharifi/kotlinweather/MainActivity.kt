package com.sharifi.kotlinweather

import android.os.Bundle
import com.sharifi.kotlinweather.base.BaseActivity
import com.sharifi.kotlinweather.home.HomeFragment
import com.sharifi.kotlinweather.util.NAME
import com.sharifi.kotlinweather.util.loadFragment

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onStart() {
        super.onStart()
        loadFragment {
            val homeFragment = HomeFragment()
            add(R.id.main_container, homeFragment, homeFragment.NAME)
            homeFragment.NAME
        }
    }
}
