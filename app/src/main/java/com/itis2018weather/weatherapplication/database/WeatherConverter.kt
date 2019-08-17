package com.itis2018weather.weatherapplication.database

import android.arch.persistence.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.itis2018weather.weatherapplication.entities.Weather

class WeatherConverter {
    @TypeConverter
    fun serialize(listOfWeather: List<Weather>): String =
        Gson().toJson(listOfWeather)

    @TypeConverter
    fun deserialize(jsonOfWeather: String): List<Weather> =
        Gson().fromJson(jsonOfWeather, object : TypeToken<List<Weather>>() {}.type)
}
