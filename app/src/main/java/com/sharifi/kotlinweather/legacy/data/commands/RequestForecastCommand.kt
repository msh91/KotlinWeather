package com.sharifi.kotlinweather.legacy.data.commands

import com.sharifi.kotlinweather.legacy.data.model.ForecastDataMapper
import com.sharifi.kotlinweather.legacy.data.model.ForecastList
import com.sharifi.kotlinweather.legacy.data.repository.ForecastProvider
import com.sharifi.kotlinweather.legacy.data.repository.ForecastRequest

/**
 * Created by sharifi on 7/21/17.
 */
class RequestForecastCommand(
        val zipCode: Long,
        val forecastProvider: ForecastProvider = ForecastProvider()) :
        Command<ForecastList> {

    companion object {
        val DAYS = 7
    }

    override fun execute(): ForecastList {
        return forecastProvider.requestByZipCode(zipCode, DAYS)
    }
}