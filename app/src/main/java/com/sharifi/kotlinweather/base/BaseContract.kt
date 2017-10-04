package com.sharifi.kotlinweather.base

import android.content.Context
import org.jetbrains.anko.toast

/**
 * Created by sharifi on 10/4/17.
 */
interface BaseView {
    var fragmentTransactionCanBeCommitted: Boolean
    val ctx: Context
    fun showError(resId: Int = 0, message: String = "") {
        if (resId != 0) {
            ctx.toast(resId)
        } else {
            ctx.toast(message)
        }
    }

    fun setProgressIndicator(active: Boolean)
}
interface BasePresenter {
    fun onCreate() {}

    fun onViewCreated() {}

    fun onStart() {}

    fun onResume() {}

    fun onStop() {}

    fun onDestroy() {}
}


class TestPresenter: BasePresenter

interface TestView: BaseView

class TestFragment: BaseFragment(), TestView {
    override fun setProgressIndicator(active: Boolean) {

    }

    override val presenter = TestPresenter()

}