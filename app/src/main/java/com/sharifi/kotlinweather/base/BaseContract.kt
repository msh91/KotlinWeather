package com.sharifi.kotlinweather.base

import android.content.Context
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import org.jetbrains.anko.toast

/**
 * Created by sharifi on 10/4/17.
 */
interface BaseView {
    var canBeShown: Boolean
    val ctx: Context
    var lazyPresenters: List<Lazy<BasePresenter<BaseView>>>
    fun <T : BasePresenter<BaseView>> presenterInitializer(init: () -> T) = lazy(init).also { lazyPresenters += it }

    /**
     * will be called immediately after BasePresenter.onViewCreated() to initial views
     */
    fun initView() {}

    /**
     * show or hide progress view (like ProgressBar)
     */
    fun setProgressIndicator(active: Boolean) {}

    fun showError(resId: Int = 0, message: String = "") {
        if (resId != 0) {
            ctx.toast(resId)
        } else {
            ctx.toast(message)
        }
    }
}

interface BasePresenter<out T : BaseView> {
    val mView: T
    val disposables: CompositeDisposable?
    fun onCreate() {}

    fun onViewCreated() {}

    fun onStart() {}

    fun onResume() {}

    fun onStop() {
        disposables?.clear()
    }

    fun addDisposable(disposable: Disposable) {disposables?.add(disposable)}

    fun onDestroy() {}
}