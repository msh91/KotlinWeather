package com.sharifi.kotlinweather.data.service

/**
 * Created by sharifi on 9/25/17.
 */
data class RestError(
        val status: RestStatus,
        val code: Int = -1,
        val message: String = ""
)