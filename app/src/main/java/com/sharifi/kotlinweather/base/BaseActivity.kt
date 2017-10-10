package com.sharifi.kotlinweather.base

import android.content.Context
import android.os.Bundle
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

abstract class BaseActivityWithPresenter : BaseActivity(), BaseView {
    override var canBeShown: Boolean = false
        get() = activityStarted
    override val ctx: Context by lazy { this }

    private var lazyPresenters: List<Lazy<BasePresenter>> = emptyList()
    fun <T : BasePresenter> presenter(init: () -> T) = lazy(init).also { lazyPresenters += it }

    fun onCreate(savedInstanceState: Bundle?, init: () -> Unit) {
        super.onCreate(savedInstanceState)
        lazyPresenters.forEach { it.value.onCreate() }
        init()
        lazyPresenters.forEach { it.value.onViewCreated() }
    }

    override fun onStart() {
        super.onStart()
        lazyPresenters.forEach { it.value.onStart() }
    }

    override fun onResume() {
        super.onResume()
        lazyPresenters.forEach { it.value.onResume() }
    }

    override fun onStop() {
        super.onStop()
        lazyPresenters.forEach { it.value.onStop() }
    }

    override fun onDestroy() {
        super.onDestroy()
        lazyPresenters.forEach { it.value.onDestroy() }
    }
}