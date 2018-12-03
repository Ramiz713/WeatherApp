package com.itis2018weather.weatherapplication

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

data class WeatherList(
    val count: Int,
    val list: List<WeatherItem>,
    val message: String
)

@Parcelize
data class WeatherItem(
    @SerializedName("coord")
    val coordinate: Coordinate,
    @SerializedName("dt")
    val dt: Int,
    @SerializedName("id")
    val id: Int,
    @SerializedName("main")
    val forecast: Forecast,
    @SerializedName("name")
    val city: String,
    @SerializedName("sys")
    val country: Country,
    @SerializedName("weather")
    val weather: List<Weather>,
    @SerializedName("wind")
    val wind: Wind
) : Parcelable

@Parcelize
data class Forecast(
    @SerializedName("humidity")
    val humidity: Double,
    @SerializedName("pressure")
    val pressure: Double,
    @SerializedName("temp")
    val temperature: Double,
    @SerializedName("temp_max")
    val maxTemperature: Double,
    @SerializedName("temp_min")
    val minTemperature: Double
) : Parcelable

@Parcelize
data class Country(
    @SerializedName("country")
    val name: String
) : Parcelable

@Parcelize
data class Weather(
    @SerializedName("description")
    val description: String,
    @SerializedName("icon")
    val icon: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("main")
    val main: String
) : Parcelable

@Parcelize
data class Wind(
    @SerializedName("deg")
    val degree: Double,
    @SerializedName("gust")
    val gust: Double,
    @SerializedName("speed")
    val speed: Double
) : Parcelable

@Parcelize
data class Coordinate(
    @SerializedName("lat")
    val latitude: Double,
    @SerializedName("lon")
    val longitude: Double
) : Parcelable
