package com.itis2018weather.weatherapplication.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.TypeConverters
import com.itis2018weather.weatherapplication.entities.WeatherItem

@Dao
@TypeConverters(WeatherConverter::class)
interface WeatherDao {
    @Query("SELECT * FROM weather_data")
    fun getAll(): List<WeatherItem>

    @Insert()
    fun insertAll(weatherData: List<WeatherItem>)

    @Query("DELETE FROM weather_data")
    fun deleteAll()
}
