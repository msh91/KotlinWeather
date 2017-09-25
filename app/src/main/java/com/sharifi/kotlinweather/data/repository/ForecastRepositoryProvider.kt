package com.sharifi.kotlinweather.data.repository

import com.sharifi.kotlinweather.data.model.Forecast

/**
 * Created by sharifi on 9/24/17.
 */
class ForecastRepositoryProvider(val sources: List<ForecastRepository> = SOURCES): ForecastRepository {
    companion object {
        val SOURCES = listOf(ForecastDbRepository())
    }
    override fun requestForecastByZipCode(zipCode: Long, date: Long, callback: ForecastRepository.ForecastListCallback) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun requestDayForecast(id: Long): Forecast? {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}