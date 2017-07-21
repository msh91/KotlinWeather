package com.sharifi.kotlinweather.home

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import android.widget.TextView
import com.sharifi.kotlinweather.data.model.ForecastList

/**
 * Created by mohammad on 7/19/17.
 */

class ForecastListAdapter(val weekForecast: ForecastList) : RecyclerView.Adapter<ForecastListAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(TextView(parent.context))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(weekForecast.dailyForecast[position]) {

            holder.tv.text = "$date - $description - $high/$low"
        }
    }

    override fun getItemCount(): Int = weekForecast.dailyForecast.size

    class ViewHolder(val tv: TextView) : RecyclerView.ViewHolder(tv)

}

