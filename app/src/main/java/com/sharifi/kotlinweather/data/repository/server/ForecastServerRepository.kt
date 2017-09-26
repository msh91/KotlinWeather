package com.sharifi.kotlinweather.data.repository.server

import com.sharifi.kotlinweather.data.model.Forecast
import com.sharifi.kotlinweather.data.model.ForecastList
import com.sharifi.kotlinweather.data.repository.ForecastRepository
import com.sharifi.kotlinweather.data.repository.server.service.ApiService
import com.sharifi.kotlinweather.data.repository.server.service.RestCallback
import com.sharifi.kotlinweather.data.repository.server.service.RestClient
import com.sharifi.kotlinweather.data.repository.server.service.RestError

/**
 * Created by sharifi on 9/24/17.
 */
class ForecastServerRepository(
        private val apiService: ApiService = RestClient.createService(ApiService::class.java),
        private val dataMapper: ServerDataMapper = ServerDataMapper()) : ForecastRepository {

    private val TAG = ForecastServerRepository::class.java.simpleName

    override fun requestForecastByZipCode(zipCode: Long, date: Long, success: (ForecastList) -> Unit, failure: (RestError) -> Unit) {
        apiService
                .requestForecastByZipCode(query = zipCode.toString())
                .enqueue(RestCallback<ForecastListResult>({
                    dataMapper.convertForecastListResultToDomain(zipCode, it)
                }, failure))
    }

    override fun requestDayForecast(id: Long, success: (Forecast) -> Unit, failure: (RestError) -> Unit) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}