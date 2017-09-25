package com.sharifi.kotlinweather.data.repository

import com.sharifi.kotlinweather.data.model.Forecast
import com.sharifi.kotlinweather.data.model.ForecastList
import com.sharifi.kotlinweather.data.service.RestError

/**
 * Created by sharifi on 9/24/17.
 */
interface ForecastRepository {
    fun requestForecastByZipCode(
            zipCode: Long,
            date: Long,
            success: (ForecastList) -> Unit,
            failure: (RestError) -> Unit
    )
    fun requestDayForecast(
            id: Long,
            success: (Forecast) -> Unit,
            failure: (RestError) -> Unit
    )
}