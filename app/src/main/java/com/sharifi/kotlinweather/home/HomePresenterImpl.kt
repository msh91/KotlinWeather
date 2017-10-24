package com.sharifi.kotlinweather.home

import android.util.Log
import com.sharifi.kotlinweather.data.repository.ForecastRepositoryProvider
import io.reactivex.disposables.CompositeDisposable

/**
 * Created by sharifi on 10/10/17.
 */
class HomePresenterImpl(
        override val mView: HomeView,
        private val forecastRepository: ForecastRepositoryProvider = ForecastRepositoryProvider.instance(),
        val zipCode: Long, override val disposables: CompositeDisposable = CompositeDisposable()

) : HomePresenter {
    private val TAG = HomePresenterImpl::class.java.simpleName


    override fun onViewCreated() {
        super.onViewCreated()
        loadForecasts(zipCode)
    }

    override fun loadForecasts(zipCode: Long) {
        Log.d(TAG, "loadForecasts() called with: zipCode = [$zipCode]")
        mView.setProgressIndicator(true)
        forecastRepository.requestForecastByZipCode(
                zipCode,
                System.currentTimeMillis() / ForecastRepositoryProvider.DAY_IN_MILLIS,
                this::addDisposable,
                {
                    Log.d(TAG, "loadForecasts() called with success: " + it)
                    if (!mView.canBeShown) {
                        return@requestForecastByZipCode
                    }
                    mView.setProgressIndicator(false)
                    mView.showForecasts(it)
                },
                {
                    Log.d(TAG, "loadForecasts() called with failure: " + it)
                    if (!mView.canBeShown) {
                        return@requestForecastByZipCode
                    }
                    mView.setProgressIndicator(false)
                    mView.showError(message = it.message)
                }
        )

    }
}