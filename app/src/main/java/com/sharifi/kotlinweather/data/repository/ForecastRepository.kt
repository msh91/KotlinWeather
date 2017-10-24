package com.sharifi.kotlinweather.data.repository

import com.sharifi.kotlinweather.data.model.Forecast
import com.sharifi.kotlinweather.data.model.ForecastList
import com.sharifi.kotlinweather.data.repository.server.service.ApiError
import io.reactivex.disposables.Disposable

/**
 * Created by sharifi on 9/24/17.
 */
interface ForecastRepository {
    fun requestForecastByZipCode(
            zipCode: Long,
            date: Long,
            success: (ForecastList) -> Unit,
            failure: (ApiError) -> Unit
    ): Disposable
    fun requestDayForecast(
            id: Long,
            success: (Forecast) -> Unit,
            failure: (ApiError) -> Unit
    )
}