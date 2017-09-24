package com.sharifi.kotlinweather.data.repository

import com.sharifi.kotlinweather.data.model.Forecast
import com.sharifi.kotlinweather.data.model.ForecastList
import com.sharifi.kotlinweather.data.service.ApiService
import com.sharifi.kotlinweather.data.service.RestClient

/**
 * Created by sharifi on 9/24/17.
 */
class ForecastServerRepository: ForecastRepository {
    override fun requestForecastByZipCode(zipCode: Long, date: Long): ForecastList? {
        val apiService: ApiService = RestClient.createService(ApiService::class.java)
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun requestDayForecast(id: Long): Forecast? {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}