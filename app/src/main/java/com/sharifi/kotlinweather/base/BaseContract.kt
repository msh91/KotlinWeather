package com.sharifi.kotlinweather.base

import android.content.Context
import android.os.Bundle
import android.os.PersistableBundle
import com.sharifi.kotlinweather.R
import org.jetbrains.anko.toast

/**
 * Created by sharifi on 10/4/17.
 */
interface BaseView {
    var canBeShown: Boolean
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

interface TestPresenter: BasePresenter
interface TestView: BaseView

class TestPresenterImpl: TestPresenter

class TestFragment: BaseFragmentWithPresenter(), TestView {
    val testPresenter: TestPresenter by presenter { TestPresenterImpl() }
    override fun setProgressIndicator(active: Boolean) {

    }
}

class TestActivity : BaseActivityWithPresenter(), TestView {
    val testPresenter: TestPresenter by presenter { TestPresenterImpl() }
    override fun setProgressIndicator(active: Boolean) {

    }

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        onCreate(savedInstanceState) {
            setContentView(R.layout.activity_main)
        }
    }

}