package com.sharifi.kotlinweather.home

import android.os.Build
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sharifi.kotlinweather.R
import com.sharifi.kotlinweather.data.Person
import com.sharifi.kotlinweather.data.commands.RequestForecastCommand
import com.sharifi.kotlinweather.detail.DetailActivity
import com.sharifi.kotlinweather.setting.SettingsActivity
import com.sharifi.kotlinweather.toolbar.ToolbarManager
import com.sharifi.kotlinweather.util.DelegateExt
import com.sharifi.kotlinweather.util.startActivity
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.find
import org.jetbrains.anko.toast
import org.jetbrains.anko.uiThread

/**
 * A placeholder fragment containing a simple view.
 */
class ForecastsFragment : Fragment(), ToolbarManager {
    override lateinit var toolbar: Toolbar
    private val TAG = ForecastsFragment::class.java.simpleName
    lateinit var forecastList: RecyclerView
    private var zipCode: Long by DelegateExt.preference(SettingsActivity.ZIP_CODE, SettingsActivity.DEFAULT_ZIP)

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val root = inflater!!.inflate(R.layout.fragment_home, container, false)

        initToolbar()

        forecastList = root.find(R.id.forecastList)
        forecastList.layoutManager = LinearLayoutManager(context)
        attachToScroll(forecastList)


        return root
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val person = Person()
        customWith(person) {
            firstName = "mohammad"
            lastName = "sharifi"
            Log.d(TAG, this.toString())
        }

        supportsLollipop {
            activity.window.statusBarColor = resources.getColor(R.color.colorPrimary, null)
        }
    }

    override fun onResume() {
        super.onResume()
        loadForecast()
    }

    private fun loadForecast() = doAsync {
        val forecastResult = RequestForecastCommand(zipCode).execute()
        Log.d(TAG, forecastResult.toString())
        uiThread {
            val adapter = ForecastListAdapter(forecastResult) {
                startActivity<DetailActivity>(
                        DetailActivity.ID to it.id,
                        DetailActivity.CITY_NAME to forecastResult.city
                )
            }
            forecastList.adapter = adapter
            toolbarTitle = "${forecastResult.city} (${forecastResult.country})"
        }
    }


    inline fun <T> customWith(t: T, body: T.() -> Unit) {
        t.body()
    }

    inline fun supportsLollipop(code: () -> Unit) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            code()
        }
    }


    private fun showToast(message: String) {
        activity.toast(message)
    }

    companion object {

        val FRAGMENT_NAME: String? = ForecastsFragment::class.java.name

        fun newInstance(toolbar: Toolbar): ForecastsFragment {

            val args = Bundle()

            val fragment = ForecastsFragment()
            fragment.arguments = args
            fragment.toolbar = toolbar
            return fragment
        }
    }
}
