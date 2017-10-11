package com.sharifi.kotlinweather.data.repository

import com.sharifi.kotlinweather.data.model.Forecast
import com.sharifi.kotlinweather.data.model.ForecastList
import com.sharifi.kotlinweather.data.repository.db.ForecastDbRepository
import com.sharifi.kotlinweather.data.repository.server.ForecastServerRepository
import com.sharifi.kotlinweather.data.repository.server.service.RestError

/**
 * Created by sharifi on 9/24/17.
 */
class ForecastRepositoryProvider private constructor(private val sources: List<ForecastRepository> = SOURCES) : ForecastRepository{
    companion object {
        val SOURCES = listOf(ForecastDbRepository(), ForecastServerRepository())
        val DAY_IN_MILLIS = 1000 * 60 * 60 * 24
        private var INSTANCE: ForecastRepositoryProvider? = null
        fun instance(sources: List<ForecastRepository> = SOURCES): ForecastRepositoryProvider {
            if (INSTANCE == null) {
                INSTANCE = ForecastRepositoryProvider(sources)
            }
            return INSTANCE!!
        }
    }

    override fun requestForecastByZipCode(zipCode: Long, date: Long, success: (ForecastList) -> Unit, failure: (RestError) -> Unit) {
        sources[0].requestForecastByZipCode(zipCode, date, { success(it) }, {
            sources[1].requestForecastByZipCode(zipCode, date, success, failure)
        })
    }

    override fun requestDayForecast(id: Long, success: (Forecast) -> Unit, failure: (RestError) -> Unit) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}