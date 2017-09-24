package com.sharifi.kotlinweather.data.service

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
 * Created by sharifi on 9/24/17.
 */
class RestClient private constructor() {
    private val TAG = RestClient::class.java.simpleName

    companion object {
        val DATE_FORMAT: String = "yyyy-MM-dd'T'HH:mm:ss"
        val instance: RestClient by lazy { RestClient() }
        fun <T> createService(serviceClass: Class<T>): T =
                instance.retrofit.create(serviceClass)
    }

    private val builder = Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(buildConverterFactory())
    private val httpClient = OkHttpClient.Builder()
            .readTimeout(20, TimeUnit.SECONDS)
            .addInterceptor(CustomInterceptor())
            .build()
    private val retrofit = builder.client(httpClient).build()

    init {
        Log.d(TAG, "init")
    }

    private fun buildConverterFactory(): GsonConverterFactory {
        val gsonBuilder = GsonBuilder()
                .setDateFormat(DATE_FORMAT)
        return GsonConverterFactory.create(gsonBuilder.create())
    }

    private inner class CustomInterceptor : Interceptor {
        override fun intercept(chain: Interceptor.Chain): Response {
            val t1 = System.nanoTime()
            val original = chain.request()
            val requestBuilder = original?.newBuilder()
            val request = requestBuilder
                    ?.url(original.url())
                    ?.header("Accept-Language", "fa")
                    ?.method(original.method(), original.body())
                    ?.build()
            Log.d(TAG, "Sending Request ${request?.url()} on ${chain.connection()} %n ${request?.headers()}")
            val response = chain.proceed(requestBuilder!!.build())
            val t2 = System.nanoTime()
            Log.d(TAG, "Received Response for ${response.request().url()} in ${((t2 - t1) / 1e6)}")
            return response

        }

    }
}