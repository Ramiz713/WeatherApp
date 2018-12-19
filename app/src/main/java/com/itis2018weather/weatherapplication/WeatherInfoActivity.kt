package com.itis2018weather.weatherapplication

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_weather_info.*

class WeatherInfoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_weather_info)
        supportActionBar?.hide()

        val weatherItem = intent.getParcelableExtra<WeatherItem>("Weather item")
        text_city.text = weatherItem.city
        text_short_descr.text = weatherItem.weather.first().description
        text_temperature.text = getString(R.string.temperature_metric, weatherItem.forecast.temperature)
        text_pressure.text = getString(R.string.pressure, weatherItem.forecast.pressure)
        text_humidity.text = getString(R.string.humidity, weatherItem.forecast.pressure)
        val wind = weatherItem.wind
        text_wind_degree.text = getString(R.string.windDegree, wind.degree)
        text_wind_speed.text = getString(R.string.windSpeed, wind.speed)
    }
}
