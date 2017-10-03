package com.sharifi.kotlinweather.base

import android.support.v4.app.Fragment

/**
 * Created by sharifi on 10/4/17.
 */
abstract class BaseFragment : Fragment() {
    var canCommitFragmentTransactions = false

    override fun onStart() {
        super.onStart()
        canCommitFragmentTransactions = true
    }

    override fun onStop() {
        super.onStop()
        canCommitFragmentTransactions = false
    }
}