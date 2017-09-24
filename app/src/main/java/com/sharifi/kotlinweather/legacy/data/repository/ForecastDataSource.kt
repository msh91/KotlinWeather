package com.sharifi.kotlinweather.legacy.data.repository

import com.sharifi.kotlinweather.legacy.data.model.Forecast
import com.sharifi.kotlinweather.legacy.data.model.ForecastList

/**
 * Created by mohammad on 8/28/17.
 */
interface ForecastDataSource {
    fun requestForecastByZipCode(zipCode: Long, date: Long): ForecastList?
    fun requestDayForecast(id: Long): Forecast?
}