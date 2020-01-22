package com.itis2018weather.weatherapplication.entities

import android.os.Parcelable
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.google.gson.annotations.SerializedName
import com.itis2018weather.weatherapplication.database.WeatherConverter
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "weather_data")
data class WeatherItem(
    @SerializedName("coord")
    @Embedded
    var coordinate: Coordinate,
    @SerializedName("dt")
    var datetime: Int,
    @PrimaryKey
    var id: Int,
    @SerializedName("main")
    @Embedded
    var forecast: Forecast,
    @SerializedName("name")
    var city: String,
    @SerializedName("sys")
    @Embedded
    var country: Country,
    @TypeConverters(WeatherConverter::class)
    var weather: List<Weather>,
    @Embedded
    var wind: Wind
) : Parcelable
