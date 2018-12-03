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
        weather_temp.text = "${weatherItem.forecast.temperature}°C"
        weather_pressure.text = "Pressure: ${weatherItem.forecast.pressure} hPa"
        weather_humidity.text = "Humidity: ${weatherItem.forecast.humidity}%"
        val wind = weatherItem.wind
        weather_wind_degree.text = "Wind degree: ${wind.degree}°"
        weather_wind_speed.text = "Wind speed: ${wind.speed} mps"
    }
}
