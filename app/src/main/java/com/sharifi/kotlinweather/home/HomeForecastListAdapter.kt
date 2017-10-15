package com.sharifi.kotlinweather.home

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sharifi.kotlinweather.R
import com.sharifi.kotlinweather.data.model.Forecast
import com.sharifi.kotlinweather.data.model.ForecastList
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_forecast.view.*

/**
 * Created by sharifi on 10/15/17.
 */
class HomeForecastListAdapter(private val forecastList: ForecastList, private val itemClick: (Forecast) -> Unit) : RecyclerView.Adapter<HomeForecastListAdapter.ForecastViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ForecastViewHolder =
            ForecastViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_forecast, parent, false), itemClick)


    override fun onBindViewHolder(holder: ForecastViewHolder?, position: Int) {
        holder?.bind(forecastList[position])
    }

    override fun getItemCount(): Int = forecastList.size

    class ForecastViewHolder(val view: View, private val itemClick: (Forecast) -> Unit) : RecyclerView.ViewHolder(view) {
        fun bind(forecast: Forecast) = with(forecast) {
            Picasso.with(itemView.context).load(iconUrl).into(itemView.icon)
            itemView.date.text = date.toString()
            itemView.description.text = description
            itemView.maxTemperature.text = high.toString()
            itemView.minTemperature.text = low.toString()
            itemView.setOnClickListener { itemClick(this) }
        }
    }
}