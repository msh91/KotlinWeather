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
    var lazyPresenters: List<Lazy<BasePresenter<BaseView>>>
    fun <T : BasePresenter<BaseView>> presenterInitializer(init: () -> T) = lazy(init).also { lazyPresenters += it }

    fun initView()
    /**
     * show or hide progress view (like ProgressBar)
     */
    fun setProgressIndicator(active: Boolean)

    fun showError(resId: Int = 0, message: String = "") {
        if (resId != 0) {
            ctx.toast(resId)
        } else {
            ctx.toast(message)
        }
    }
}

interface BasePresenter<out T: BaseView> {
    val mView: T
    fun onCreate() {}

    fun onViewCreated() {}

    fun onStart() {}

    fun onResume() {}

    fun onStop() {}

    fun onDestroy() {}
}

interface TestPresenter : BasePresenter<TestView>
interface TestView : BaseView

class TestPresenterImpl(override val mView: TestView) : TestPresenter

class TestFragment : BaseFragmentWithPresenter(), TestView {
    val testPresenter: TestPresenter by presenterInitializer { TestPresenterImpl(this) }

    override fun initView() {

    }

    override fun setProgressIndicator(active: Boolean) {

    }
}

class TestActivity : BaseActivityWithPresenter(), TestView {
    val testPresenter: TestPresenter by presenterInitializer { TestPresenterImpl(this) }

    override fun initView() {

    }

    override fun setProgressIndicator(active: Boolean) {

    }

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        onCreate(savedInstanceState) {
            setContentView(R.layout.activity_main)
        }
    }

}