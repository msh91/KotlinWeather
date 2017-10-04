package com.sharifi.kotlinweather.base

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.View

/**
 * Created by sharifi on 10/4/17.
 */
abstract class BaseFragment : Fragment(), BaseView {
    override var fragmentTransactionCanBeCommitted: Boolean = false
    override val ctx: Context by lazy { context }
    abstract val presenter: BasePresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter.onCreate()
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.onViewCreated()
    }

    override fun onStart() {
        super.onStart()
        fragmentTransactionCanBeCommitted = true
        presenter.onStart()
    }

    override fun onResume() {
        super.onResume()
        presenter.onResume()
    }

    override fun onStop() {
        super.onStop()
        fragmentTransactionCanBeCommitted = false
        presenter.onStop()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onDestroy()
    }
}