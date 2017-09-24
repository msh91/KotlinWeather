package com.antonioleiva.weatherapp.data.server

import com.sharifi.kotlinweather.legacy.data.db.ForecastDb
import com.sharifi.kotlinweather.legacy.data.model.Forecast
import com.sharifi.kotlinweather.legacy.data.model.ForecastList
import com.sharifi.kotlinweather.legacy.data.repository.ForecastDataSource

class ForecastServer(val dataMapper: ServerDataMapper = ServerDataMapper(),
                     val forecastDb: ForecastDb = ForecastDb()) : ForecastDataSource {
    override fun requestDayForecast(id: Long): Forecast?
            = throw UnsupportedOperationException()

    override fun requestForecastByZipCode(zipCode: Long, date: Long): ForecastList? {
        val result = ForecastByZipCodeRequest(zipCode).execute()
        val converted = dataMapper.convertToDomain(zipCode, result)
        forecastDb.saveForecast(converted)
        return forecastDb.requestForecastByZipCode(zipCode, date)
    }
}