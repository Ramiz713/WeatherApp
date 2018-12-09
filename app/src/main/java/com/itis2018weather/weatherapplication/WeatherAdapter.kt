package com.itis2018weather.weatherapplication

import android.support.v7.recyclerview.extensions.ListAdapter
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.weather_item.*

class WeatherAdapter(private val listener: (Int) -> Unit) :
    ListAdapter<WeatherItem, WeatherAdapter.CityHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CityHolder =
        CityHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.weather_item, parent, false)
        )

    override fun onBindViewHolder(holder: CityHolder, position: Int) {
        holder.bind(getItem(position))
        holder.itemView.setOnClickListener { listener(position) }
    }

    class CityHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView), LayoutContainer {
        fun bind(item: WeatherItem) = with(item) {
            weather_city.text = city
            weather_country.text = if (country.name.isEmpty()) "Russia" else country.name
            weather_tmp.text = "${forecast.temperature}°C"
        }
    }
}
