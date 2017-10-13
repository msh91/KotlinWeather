package com.sharifi.kotlinweather.data.repository.server

import com.sharifi.kotlinweather.data.model.Forecast
import com.sharifi.kotlinweather.data.model.ForecastList
import com.sharifi.kotlinweather.data.repository.ForecastRepository
import com.sharifi.kotlinweather.data.repository.server.service.Api
import com.sharifi.kotlinweather.data.repository.server.service.ApiCallback
import com.sharifi.kotlinweather.data.repository.server.service.ApiError
import com.sharifi.kotlinweather.data.repository.server.service.ApiService

/**
 * Created by sharifi on 9/24/17.
 */
class ForecastServerRepository(
        private val apiService: ApiService = Api.instance(),
        private val dataMapper: ServerDataMapper = ServerDataMapper()) : ForecastRepository {

    private val TAG = ForecastServerRepository::class.java.simpleName

    override fun requestForecastByZipCode(zipCode: Long, date: Long, success: (ForecastList) -> Unit, failure: (ApiError) -> Unit) {
        apiService
                .requestForecastByZipCode(query = zipCode.toString())
                .enqueue(ApiCallback<ForecastListResult>({
                    success(dataMapper.convertForecastListResultToDomain(zipCode, it))
                }, failure))
    }

    override fun requestDayForecast(id: Long, success: (Forecast) -> Unit, failure: (ApiError) -> Unit) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}