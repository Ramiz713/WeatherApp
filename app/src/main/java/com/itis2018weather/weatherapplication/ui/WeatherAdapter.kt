package com.itis2018weather.weatherapplication.ui

import android.support.v7.recyclerview.extensions.ListAdapter
import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.itis2018weather.weatherapplication.R
import com.itis2018weather.weatherapplication.entities.WeatherItem
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.weather_item.*

class WeatherAdapter(private val listener: (Int) -> Unit) :
    ListAdapter<WeatherItem, WeatherAdapter.CityHolder>(
        DiffCallback
    ) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CityHolder =
        CityHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.weather_item, parent, false)
        )

    override fun onBindViewHolder(holder: CityHolder, position: Int) {
        holder.bind(getItem(position))
        holder.itemView.setOnClickListener { listener(position) }
    }

    class CityHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView),
        LayoutContainer {
        fun bind(item: WeatherItem) = with(item) {
            val context = containerView.context
            text_city.text = city
            weather_country.text =
                if (country.name.isEmpty()) context.getString(R.string.country) else country.name
            weather_tmp.text = context.getString(R.string.temperature_metric, forecast.temperature)
            Glide.with(containerView)
                .load("http://openweathermap.org/img/wn/${weather.first().icon}@2x.png")
                .into(image_weather_icon)
        }
    }

    object DiffCallback : DiffUtil.ItemCallback<WeatherItem>() {
        override fun areItemsTheSame(oldItem: WeatherItem?, newItem: WeatherItem?): Boolean =
            oldItem?.id == newItem?.id

        override fun areContentsTheSame(oldItem: WeatherItem?, newItem: WeatherItem): Boolean =
            oldItem == newItem
    }
}
