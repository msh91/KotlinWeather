package com.sharifi.kotlinweather.data.repository.server

/**
 * Created by sharifi on 9/26/17.
 */
data class ForecastListResult(
        val city: CityResult,
        val list: List<ForecastResult>
)

data class CityResult(
        val id: Int,
        val name: String,
        val coord: CoordinatesResult,
        val country: String
)

data class CoordinatesResult(
        val lon: Float,
        val lat: Float
)

data class ForecastResult(
        val id: Long,
        val dt: Long,
        val temp: TemperatureResult,
        val pressure: Float,
        val humidity: Int,
        val weather: List<WeatherResult>,
        val speed: Float,
        val deg: Int,
        val clouds: Int
)

data class TemperatureResult(
        val day: Float,
        val min: Float,
        val max: Float,
        val night: Float,
        val eve: Float,
        val morn: Float
)

data class WeatherResult(
        val id: Long,
        val main: String,
        val description: String,
        val icon: String
)