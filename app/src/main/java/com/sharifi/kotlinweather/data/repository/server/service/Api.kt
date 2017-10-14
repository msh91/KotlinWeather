package com.sharifi.kotlinweather.data.repository.server.service

import android.util.Log
import com.google.gson.GsonBuilder
import com.sharifi.kotlinweather.util.Constants
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Created by sharifi on 10/13/17.
 */
object Api {
    private val TAG = Api::class.java.simpleName
    val DATE_FORMAT: String = "yyyy-MM-dd'T'HH:mm:ss"

    inline fun <reified T> instance(): T =
            retrofit.create(T::class.java)

    val retrofit: Retrofit by lazy {
        val builder = Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(buildConverterFactory())
        val httpClient = OkHttpClient.Builder()
                .connectTimeout(20, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .addInterceptor(customInterceptor())
                .build()
        builder.client(httpClient).build()
    }

    private fun customInterceptor(): (Interceptor.Chain) -> Response = {
        val t1 = System.nanoTime()
        val original = it.request()
        val requestBuilder = original?.newBuilder()
        val request = requestBuilder?.apply {
            url(original.url())
            header("Accept-Language", "fa")
            method(original.method(), original.body())
        }?.build()

        Log.d(TAG, "Sending ${request?.method()} Request ${request?.url()} \nHeaders:  ${request?.headers()} \nBody: ${request?.body()}")
        val response = it.proceed(requestBuilder!!.build())
        val t2 = System.nanoTime()
        Log.d(TAG, "Received ${response.code()} for ${request?.method()} ${response.request().url()} in ${((t2 - t1) / 1e6).toInt()} milliseconds")
        response
    }

    private fun buildConverterFactory(): GsonConverterFactory {
        val gsonBuilder = GsonBuilder()
                .setDateFormat(DATE_FORMAT)
        return GsonConverterFactory.create(gsonBuilder.create())
    }

}