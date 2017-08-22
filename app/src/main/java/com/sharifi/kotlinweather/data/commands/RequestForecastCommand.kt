package com.sharifi.kotlinweather.data.commands

import com.sharifi.kotlinweather.data.model.ForecastDataMapper
import com.sharifi.kotlinweather.data.model.ForecastList
import com.sharifi.kotlinweather.data.repository.ForecastRequest

/**
 * Created by sharifi on 7/21/17.
 */
class RequestForecastCommand(val zipcode: Long) : Command<ForecastList> {

    override fun execute(): ForecastList {
        val forecastRequest = ForecastRequest(zipcode)
        return ForecastDataMapper().convertFromDataModel(zipcode, forecastRequest.execute())
    }

}