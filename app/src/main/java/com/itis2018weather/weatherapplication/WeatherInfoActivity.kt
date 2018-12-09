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
        weather_city.text = weatherItem.city
        weather_descr.text = weatherItem.weather.first().description
        weather_temp.text = getString(R.string.temperature_metric, weatherItem.forecast.temperature.toString())
        weather_pressure.text = getString(R.string.pressure, weatherItem.forecast.pressure.toString())
        weather_humidity.text = getString(R.string.humidity, weatherItem.forecast.pressure.toString())
        val wind = weatherItem.wind
        weather_wind_degree.text = getString(R.string.windDegree, wind.degree.toString())
        weather_wind_speed.text = getString(R.string.windSpeed, wind.speed.toString())
    }
}
