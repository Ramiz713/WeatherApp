package com.itis2018weather.weatherapplication.database

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import android.arch.persistence.room.TypeConverters
import com.itis2018weather.weatherapplication.WeatherItem

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
