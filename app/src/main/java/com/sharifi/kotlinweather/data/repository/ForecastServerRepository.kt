package com.sharifi.kotlinweather.data.repository

import com.sharifi.kotlinweather.data.model.Forecast
import com.sharifi.kotlinweather.data.model.ForecastList
import com.sharifi.kotlinweather.data.service.ApiService
import com.sharifi.kotlinweather.data.service.RestClient

/**
 * Created by sharifi on 9/24/17.
 */
class ForecastServerRepository(val apiService: ApiService = RestClient.createService(ApiService::class.java)): ForecastRepository {
    override fun requestForecastByZipCode(zipCode: Long, date: Long): ForecastList? {
        val response = apiService.requestForecastByZipCode(query = zipCode.toString()).execute()
        return if (response.isSuccessful) response.body() else null
    }

    override fun requestDayForecast(id: Long): Forecast? {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}