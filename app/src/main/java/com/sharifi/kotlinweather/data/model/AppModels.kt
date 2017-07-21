package com.sharifi.kotlinweather.data.model

/**
 * Created by sharifi on 7/21/17.
 */
data class ForecastList(
        val city: String,
        val country: String,
        val dailyForecast: List<Forecast>
)

data class Forecast(
        val date: String,
        val description: String,
        val high: Int,
        val low: Int
)