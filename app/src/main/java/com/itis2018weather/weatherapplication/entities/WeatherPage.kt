package com.itis2018weather.weatherapplication.entities

data class WeatherPage(
    var count: Int,
    var list: List<WeatherItem>,
    var message: String
)
