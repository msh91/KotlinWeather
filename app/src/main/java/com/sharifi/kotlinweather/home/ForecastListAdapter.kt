package com.sharifi.kotlinweather.home

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import android.widget.TextView

/**
 * Created by mohammad on 7/19/17.
 */

class ForecastListAdapter(val items: List<String>) : RecyclerView.Adapter<ForecastListAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(TextView(parent.context))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.tv.text = items[position]
    }

    override fun getItemCount(): Int = items.size

    class ViewHolder(val tv: TextView) : RecyclerView.ViewHolder(tv)

}

