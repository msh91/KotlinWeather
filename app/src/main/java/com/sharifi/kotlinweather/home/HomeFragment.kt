package com.sharifi.kotlinweather.home

import android.os.Bundle
import android.support.v7.widget.Toolbar
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sharifi.kotlinweather.R
import com.sharifi.kotlinweather.base.BaseFragmentWithPresenter
import com.sharifi.kotlinweather.toolbar.ToolbarManager

/**
 * Created by sharifi on 10/10/17.
 */
class HomeFragment: BaseFragmentWithPresenter(), HomeView, ToolbarManager {
    override lateinit var toolbar: Toolbar
    private val TAG = HomeFragment::class.java.simpleName
    private val mPresenter: HomePresenter by presenter { HomePresenterImpl() }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val root = inflater.inflate(R.layout.fragment_home, container, false)
        findViews(root)
        return root
    }

    override fun findViews(root: View) {
        toolbar = root.findViewById(R.id.toolbar)
        toolbarTitle = getString(R.string.app_name)
        initToolbarMenu(R.menu.menu_main)
    }
    override fun setProgressIndicator(active: Boolean) {
        Log.d(TAG, "setProgressIndicator() called with: active = [$active]")

    }
}