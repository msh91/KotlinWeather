package com.sharifi.kotlinweather.data.repository

/**
 * Created by sharifi on 7/20/17.
 */

/**
 * Forecast model
 */
data class Forecast(
        val id: Long,
        val dt: Long,
        val temp: Temperature,
        val pressure: Float,
        val humidity: Int,
        val weather: List<Weather>,
        val speed: Float,
        val deg: Int,
        val clouds: Int,
        val rain: Float
)

/**
 * City model
 */
data class City(
        val id: Long,
        val name: String,
        val coord: Coordinate,
        val country: String,
        val population: Int
)

/**
 * Coordinates model
 */
data class Coordinate(
        val lon: Float,
        val lat: Float
)

data class ForecastResult(
        val city: City,
        val list: List<Forecast>
)

/**
 * Temperature model
 */
data class Temperature(
        val day: Float,
        val min: Float,
        val max: Float,
        val night: Float,
        val eve: Float,
        val morn: Float
)

/**
 * Weather model
 */
data class Weather(
        val id: Long,
        val main: String,
        val description: String,
        val icon: String
)