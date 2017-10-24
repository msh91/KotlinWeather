package com.sharifi.kotlinweather.data.repository.server.service

import com.sharifi.kotlinweather.data.repository.server.ForecastListResult
import com.sharifi.kotlinweather.util.Constants
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by sharifi on 9/24/17.
 */
interface ApiService {
    @GET("data/2.5/forecast/daily?mode=json&units=metric&cnt=7")
    fun requestForecastByZipCode(
            @Query("APPID") appId: String = Constants.APP_ID,
            @Query("q") query: String
    ): Observable<ForecastListResult>
}