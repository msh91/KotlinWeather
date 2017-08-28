package com.sharifi.kotlinweather.home

import android.os.Build
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sharifi.kotlinweather.R
import com.sharifi.kotlinweather.data.Person
import com.sharifi.kotlinweather.data.commands.RequestForecastCommand
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.find
import org.jetbrains.anko.toast
import org.jetbrains.anko.uiThread

/**
 * A placeholder fragment containing a simple view.
 */
class ForecastsFragment : Fragment() {
    private val items = listOf(
            "Mon 6/23 - Sunny - 31/17",
            "Tue 6/24 - Foggy - 21/8",
            "Wed 6/25 - Cloudy - 22/17",
            "Thurs 6/26 - Rainy - 18/11",
            "Fri 6/27 - Foggy - 21/10",
            "Sat 6/28 - TRAPPED IN WEATHER STATION - 23/18",
            "Sun 6/29 - Sunny - 20/7"
    )
    private val TAG = ForecastsFragment::class.java.simpleName
    lateinit var forecastList: RecyclerView

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val root = inflater!!.inflate(R.layout.fragment_home, container, false)
        forecastList = root.find(R.id.forecastList)
        forecastList.layoutManager = LinearLayoutManager(context)

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

        doAsync {
            val forecastResult = RequestForecastCommand(123654).execute()
            Log.d(TAG, forecastResult.toString())
            uiThread {
                forecastList.adapter = ForecastListAdapter(forecastResult) { forecast -> showToast(forecast.description) }
            }
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

        fun newInstance(): ForecastsFragment {

            val args = Bundle()

            val fragment = ForecastsFragment()
            fragment.arguments = args
            return fragment
        }
    }
}
