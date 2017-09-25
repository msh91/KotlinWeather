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
class ForecastServerRepository(private val apiService: ApiService = RestClient.createService(ApiService::class.java)) : ForecastRepository {
    private val TAG = ForecastServerRepository::class.java.simpleName

    override fun requestForecastByZipCode(zipCode: Long, date: Long, success: (ForecastList) -> Unit, failure: (RestError) -> Unit) {
        apiService
                .requestForecastByZipCode(query = zipCode.toString())
                .enqueue(RestCallback<ForecastList>(success, failure))
    }

    override fun requestDayForecast(id: Long, success: (Forecast) -> Unit, failure: (RestError) -> Unit) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}