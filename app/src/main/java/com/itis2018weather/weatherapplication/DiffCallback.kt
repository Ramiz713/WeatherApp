package com.itis2018weather.weatherapplication

import android.support.v7.util.DiffUtil

class DiffCallback : DiffUtil.ItemCallback<WeatherItem>() {
    override fun areItemsTheSame(oldItem: WeatherItem?, newItem: WeatherItem?): Boolean =
        oldItem?.id == newItem?.id

    override fun areContentsTheSame(oldItem: WeatherItem?, newItem: WeatherItem): Boolean =
        oldItem == newItem
}
