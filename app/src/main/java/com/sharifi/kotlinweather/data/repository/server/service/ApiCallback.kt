package com.sharifi.kotlinweather.data.repository.server.service

import android.util.Log
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by sharifi on 9/25/17.
 */
class ApiCallback<T>(private val response: (body: T) -> Unit, private val failure: (error: ApiError) -> Unit) : Callback<T> {
    private val TAG = ApiCallback::class.java.simpleName

    override fun onFailure(call: Call<T>, t: Throwable) {
        Log.d(TAG, "${call.request().url()} onFailure: " + t.message)
        t.printStackTrace()
        failure(ApiError(RestStatus.NO_CONNECTION))
    }

    override fun onResponse(call: Call<T>, response: Response<T>) {
        if (!response.isSuccessful) {
            try {
                Log.d(TAG, "onResponse: errorBody: ${response.errorBody()?.string()}")
            } catch (t: Throwable) {

            }
            failure(ApiError(RestStatus.BAD_RESPONSE, response.code()))
            return
        }
        val body = response.body()

        return if (body == null || (body is List<*> && body.isEmpty()))
            failure(ApiError(RestStatus.EMPTY_RESPONSE, response.code()))
        else
            response(body)
    }


}