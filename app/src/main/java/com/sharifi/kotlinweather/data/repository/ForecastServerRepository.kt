package com.sharifi.kotlinweather.data.repository

import com.sharifi.kotlinweather.data.model.Forecast
import com.sharifi.kotlinweather.data.model.ForecastList
import com.sharifi.kotlinweather.data.service.ApiService
import com.sharifi.kotlinweather.data.service.RestCallback
import com.sharifi.kotlinweather.data.service.RestClient
import com.sharifi.kotlinweather.data.service.RestError

/**
 * Created by sharifi on 9/24/17.
 */
class ForecastServerRepository(val apiService: ApiService = RestClient.createService(ApiService::class.java)) : ForecastRepository {
    private val TAG = ForecastServerRepository::class.java.simpleName

    override fun requestForecastByZipCode(zipCode: Long, date: Long, callback: ForecastRepository.ForecastListCallback) {
        apiService
                .requestForecastByZipCode(query = zipCode.toString())
                .enqueue(RestCallback<ForecastList>(forecastFailure(), forecastResponse()))
    }

    private fun forecastResponse(): (ForecastList) -> Unit = {}
    private fun forecastFailure(): (RestError) -> Unit = {}


    override fun requestDayForecast(id: Long): Forecast? {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}