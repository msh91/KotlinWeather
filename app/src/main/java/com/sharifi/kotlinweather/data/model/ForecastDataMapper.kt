package com.sharifi.kotlinweather.data.model

import com.sharifi.kotlinweather.data.repository.Forecast
import com.sharifi.kotlinweather.data.repository.ForecastResult
import java.text.DateFormat
import java.util.*
import com.sharifi.kotlinweather.data.model.Forecast as ModelForecast
/**
 * Created by sharifi on 7/21/17.
 */
public class ForecastDataMapper {

    fun convertFromDataModel(forecastResult: ForecastResult): ForecastList {
        return ForecastList(forecastResult.city.name, forecastResult.city.country, convertToForecastList(forecastResult.list))
    }

    private fun convertToForecastList(list: List<Forecast>) : List<ModelForecast> {
        return list.map { convertToModelForecast(it) }
    }

    private fun convertToModelForecast(forecast: Forecast): ModelForecast {
        return ModelForecast(
                convertDate(forecast.dt),
                forecast.weather[0].description,
                forecast.temp.max.toInt(),
                forecast.temp.min.toInt(),
                generateIconUrl(forecast.weather[0].icon)
        )
    }

    private fun generateIconUrl(icon: String): String = "http://openweathermap.org/img/w/$icon.png"

    private fun convertDate(date: Long): String {
        val df = DateFormat.getDateInstance(DateFormat.MEDIUM, Locale.getDefault())
        return df.format(date * 1000)
    }
}