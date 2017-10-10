package com.sharifi.kotlinweather.base

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.View

/**
 * Created by sharifi on 10/4/17.
 */

abstract class BaseFragment : Fragment() {
    var fragmentStarted: Boolean = false

    override fun onStart() {
        super.onStart()
        fragmentStarted = true
    }

    override fun onStop() {
        super.onStop()
        fragmentStarted = false
    }
}

abstract class BaseFragmentWithPresenter : BaseFragment(), BaseView {
    private val BASE_TAG = BaseFragmentWithPresenter::class.java.simpleName
    override var canBeShown: Boolean = false
        get() = fragmentStarted
    override val ctx: Context by lazy { context }

    fun <T : BasePresenter> presenter(init: () -> T) = lazy(init).also { lazyPresenters += it }

    private var lazyPresenters: List<Lazy<BasePresenter>> = emptyList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lazyPresenters.forEach { it.value.onCreate() }
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lazyPresenters.forEach { it.value.onViewCreated() }
    }

    override fun onStart() {
        super.onStart()
        Log.d(BASE_TAG, "onStart: canBeShown: $canBeShown , started: $fragmentStarted ")
        lazyPresenters.forEach { it.value.onStart() }

    }

    override fun onResume() {
        super.onResume()
        lazyPresenters.forEach { it.value.onResume() }
    }

    override fun onStop() {
        super.onStop()
        Log.d(BASE_TAG, "onStart: canBeShown: $canBeShown , started: $fragmentStarted ")
        lazyPresenters.forEach { it.value.onStop() }
    }

    override fun onDestroy() {
        super.onDestroy()
        lazyPresenters.forEach { it.value.onDestroy() }
    }
}