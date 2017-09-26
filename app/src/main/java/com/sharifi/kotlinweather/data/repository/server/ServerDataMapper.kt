package com.sharifi.kotlinweather.data.repository.server

import com.sharifi.kotlinweather.data.model.Forecast
import com.sharifi.kotlinweather.data.model.ForecastList
import com.sharifi.kotlinweather.util.Constants
import java.util.*
import java.util.concurrent.TimeUnit

/**
 * Created by sharifi on 9/26/17.
 */
class ServerDataMapper {
    fun convertForecastListResultToDomain(zipCode: Long, result: ForecastListResult) =
            with(result) {
                ForecastList(zipCode, city.name, city.country, convertForecastResultListToDomain(list))
            }

    private fun convertForecastResultListToDomain(list: List<ForecastResult>): List<Forecast> {
        return list.mapIndexed { index, result ->
            val dt = Calendar.getInstance().timeInMillis + TimeUnit.DAYS.toMillis(index.toLong())
            convertForecastResultToDomain(result.copy(dt = dt))
        }
    }

    private fun convertForecastResultToDomain(result: ForecastResult) = with(result) {
        Forecast(
                id,
                dt,
                weather[0].description,
                temp.max.toInt(),
                temp.min.toInt(),
                generateIconUrl(weather[0].icon)
        )
    }

    private fun generateIconUrl(iconCode: String): String = Constants.BASE_URL + "/img/w/$iconCode.png"
}