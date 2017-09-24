package com.sharifi.kotlinweather.data.repository

import com.sharifi.kotlinweather.data.model.Forecast
import com.sharifi.kotlinweather.data.model.ForecastList

/**
 * Created by sharifi on 9/24/17.
 */
interface ForecastRepository {
    fun requestForecastByZipCode(zipCode: Long, date: Long): ForecastList?
    fun requestDayForecast(id: Long): Forecast?
}