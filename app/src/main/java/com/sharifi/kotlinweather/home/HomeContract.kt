package com.sharifi.kotlinweather.home

import com.sharifi.kotlinweather.base.BasePresenter
import com.sharifi.kotlinweather.base.BaseView
import com.sharifi.kotlinweather.data.model.Forecast
import com.sharifi.kotlinweather.data.model.ForecastList

/**
 * Created by sharifi on 10/10/17.
 */
interface HomePresenter: BasePresenter<HomeView> {
    fun loadForecasts(zipCode: Long)
}

interface HomeView: BaseView {
    fun showForecasts(forecasts: ForecastList)
    fun onForecastSelected(forecast: Forecast)
}