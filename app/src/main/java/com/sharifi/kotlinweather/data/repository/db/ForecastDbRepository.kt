package com.sharifi.kotlinweather.data.repository.db

import com.sharifi.kotlinweather.data.model.Forecast
import com.sharifi.kotlinweather.data.model.ForecastList
import com.sharifi.kotlinweather.data.repository.ForecastRepository
import com.sharifi.kotlinweather.data.repository.server.service.ApiError
import com.sharifi.kotlinweather.data.repository.server.service.ApiError.ApiStatus
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable

/**
 * Created by sharifi on 9/24/17.
 */
class ForecastDbRepository : ForecastRepository {
    override fun requestForecastByZipCode(zipCode: Long, date: Long, success: (ForecastList) -> Unit, failure: (ApiError) -> Unit): Disposable {
        return Observable
                .just("sample")
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe { failure(ApiError(ApiStatus.EMPTY_RESPONSE)) }
    }

    override fun requestDayForecast(id: Long, success: (Forecast) -> Unit, failure: (ApiError) -> Unit) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}