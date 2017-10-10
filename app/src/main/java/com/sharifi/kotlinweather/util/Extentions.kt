package com.sharifi.kotlinweather.util

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import android.support.v4.app.FragmentTransaction
import com.sharifi.kotlinweather.base.BaseActivity
import com.sharifi.kotlinweather.base.BaseFragment

/**
 * Created by sharifi on 10/4/17.
 */

fun FragmentActivity.loadFragment(loadToBackStack: Boolean = false, load: FragmentTransaction.() -> String) {
    if (this is BaseActivity && !activityStarted) {
        return
    }
    val transaction = supportFragmentManager.beginTransaction()
    val fragmentName = transaction.load()
    if (loadToBackStack) {
        transaction.addToBackStack(fragmentName)
    }
    transaction.commit()

}

fun Fragment.loadFragment(loadToBackStack: Boolean = false, useChildFragmentManager: Boolean = false, load: FragmentTransaction.() -> String) {
    if (this is BaseFragment && !fragmentStarted) {
        return
    }

    if (!useChildFragmentManager) {
        activity.loadFragment(loadToBackStack, load)
        return
    }

    val transaction = childFragmentManager.beginTransaction()
    val fragmentName = transaction.load()
    if (loadToBackStack) {
        transaction.addToBackStack(fragmentName)
    }
    transaction.commit()

}