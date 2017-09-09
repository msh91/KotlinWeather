package com.sharifi.kotlinweather.data.commands

import com.sharifi.kotlinweather.data.model.Forecast
import com.sharifi.kotlinweather.data.repository.ForecastProvider

/**
 * Created by sharifi on 9/9/17.
 */
class RequestDayForecastCommand(
        val id: Long,
        val forecastProvider: ForecastProvider = ForecastProvider()) : Command<Forecast> {

    override fun execute() = forecastProvider.requestForecast(id)

}