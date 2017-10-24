package com.sharifi.kotlinweather.data.repository.server

import com.sharifi.kotlinweather.data.model.Forecast
import com.sharifi.kotlinweather.data.model.ForecastList
import com.sharifi.kotlinweather.data.repository.ForecastRepository
import com.sharifi.kotlinweather.data.repository.server.service.Api
import com.sharifi.kotlinweather.data.repository.server.service.ApiDisposable
import com.sharifi.kotlinweather.data.repository.server.service.ApiError
import com.sharifi.kotlinweather.data.repository.server.service.ApiService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable

/**
 * Created by sharifi on 9/24/17.
 */
class ForecastServerRepository(
        private val apiService: ApiService = Api.instance(),
        private val dataMapper: ServerDataMapper = ServerDataMapper()) : ForecastRepository {

    private val TAG = ForecastServerRepository::class.java.simpleName

    override fun requestForecastByZipCode(zipCode: Long, date: Long, success: (ForecastList) -> Unit, failure: (ApiError) -> Unit): Disposable {
        return apiService
                .requestForecastByZipCode(query = zipCode.toString())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(ApiDisposable({
                    success(dataMapper.convertForecastListResultToDomain(zipCode, it))
                }, failure))
    }

    override fun requestDayForecast(id: Long, success: (Forecast) -> Unit, failure: (ApiError) -> Unit) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}