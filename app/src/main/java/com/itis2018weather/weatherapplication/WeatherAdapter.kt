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

    override fun onBindViewHolder(holder: CityHolder, position: Int) =
        holder.bind(getItem(position), listener)

    class CityHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView), LayoutContainer {
        fun bind(item: WeatherItem, listener: (Int) -> Unit) = with(containerView) {
            weather_city.text = item.city
            weather_country.text = if (item.country.name.isEmpty()) "Russia" else item.country.name
            weather_tmp.text = "${item.forecast.temperature}°C"
            setOnClickListener {
                listener(adapterPosition)
            }
        }
    }
}
