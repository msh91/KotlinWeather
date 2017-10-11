package com.sharifi.kotlinweather.home

import android.util.Log
import com.sharifi.kotlinweather.data.repository.ForecastRepositoryProvider

/**
 * Created by sharifi on 10/10/17.
 */
class HomePresenterImpl(
        override val mView: HomeView,
        val forecastRepositoy: ForecastRepositoryProvider = ForecastRepositoryProvider.instance(),
        val zipCode: Long

) : HomePresenter {
    private val TAG = HomePresenterImpl::class.java.simpleName

    override fun onViewCreated() {
        super.onViewCreated()
        loadForecasts(zipCode)
    }

    override fun loadForecasts(zipCode: Long) {
        Log.d(TAG, "loadForecasts() called with: zipCode = [$zipCode]")
        mView.setProgressIndicator(true)
        forecastRepositoy.requestForecastByZipCode(
                zipCode,
                System.currentTimeMillis() / ForecastRepositoryProvider.DAY_IN_MILLIS,
                {
                    mView.setProgressIndicator(false)
                    Log.d(TAG, "loadForecasts() called with success: " + it)
                },
                {
                    mView.setProgressIndicator(false)
                    Log.d(TAG, "loadForecasts() called with failure: " + it)
                }
        )
    }


}