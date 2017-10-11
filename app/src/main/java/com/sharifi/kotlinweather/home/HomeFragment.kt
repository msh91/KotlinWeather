package com.sharifi.kotlinweather.home

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.Toolbar
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sharifi.kotlinweather.R
import com.sharifi.kotlinweather.base.BaseFragmentWithPresenter
import com.sharifi.kotlinweather.toolbar.ToolbarManager
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.toolbar.*

/**
 * Created by sharifi on 10/10/17.
 */
class HomeFragment : BaseFragmentWithPresenter(), HomeView, ToolbarManager {
    override lateinit var toolbar: Toolbar
    private val TAG = HomeFragment::class.java.simpleName
    private val mPresenter: HomePresenter by presenterInitializer { HomePresenterImpl(this) }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
            inflater.inflate(R.layout.fragment_home, container, false)

    override fun initView() {
        toolbar = appToolbar
        toolbarTitle = getString(R.string.app_name)
        initToolbarMenu(R.menu.menu_main)

        forecastList.layoutManager = LinearLayoutManager(context)
        attachToScroll(forecastList)
    }
}