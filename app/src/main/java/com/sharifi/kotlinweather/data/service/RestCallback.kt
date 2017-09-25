package com.sharifi.kotlinweather.data.service

import android.util.Log
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by sharifi on 9/25/17.
 */
abstract class RestCallback<T> : Callback<T> {
    private val TAG = RestCallback::class.java.simpleName

    abstract fun onResponse(body: T)
    abstract fun onFailure(error: RestError)

    override fun onFailure(call: Call<T>, t: Throwable) {
        Log.d(TAG, "${call.request().url()} onFailure: " + t.message)
        t.printStackTrace()
        onFailure(RestError(RestStatus.NO_CONNECTION))
    }

    override fun onResponse(call: Call<T>, response: Response<T>) {
        Log.d(TAG, "${call.request().url()} onResponse() called with: response = [${response.code()}]")
        if (!response.isSuccessful) {
            try {
                Log.d(TAG, "onResponse: errorBody: ${response.errorBody()?.string()}")
            } catch (t: Throwable) {

            }
            onFailure(RestError(RestStatus.BAD_RESPONSE, response.code()))
            return
        }
        val body = response.body()
        Log.d(TAG, "onResponse() called with body: $body")
        if (body == null || (body is List<*> && body.isEmpty())) {
            onFailure(RestError(RestStatus.BAD_RESPONSE, response.code()))
            return
        }
        onResponse(body)
    }


}