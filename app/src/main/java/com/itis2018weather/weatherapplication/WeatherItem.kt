package com.itis2018weather.weatherapplication

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

data class WeatherList(
    var count: Int,
    var list: List<WeatherItem>,
    var message: String
)

@Parcelize
data class WeatherItem(
    @SerializedName("coord")
    var coordinate: Coordinate,
    @SerializedName("dt")
    var dt: Int,
    @SerializedName("id")
    var id: Int,
    @SerializedName("main")
    var forecast: Forecast,
    @SerializedName("name")
    var city: String,
    @SerializedName("sys")
    var country: Country,
    @SerializedName("weather")
    var weather: List<Weather>,
    @SerializedName("wind")
    var wind: Wind
) : Parcelable

@Parcelize
data class Forecast(
    @SerializedName("humidity")
    var humidity: Double,
    @SerializedName("pressure")
    var pressure: Double,
    @SerializedName("temp")
    var temperature: Double,
    @SerializedName("temp_max")
    var maxTemperature: Double,
    @SerializedName("temp_min")
    var minTemperature: Double
) : Parcelable

@Parcelize
data class Country(
    @SerializedName("country")
    var name: String
) : Parcelable

@Parcelize
data class Weather(
    @SerializedName("description")
    var description: String,
    @SerializedName("icon")
    var icon: String,
    @SerializedName("id")
    var id: Int,
    @SerializedName("main")
    var main: String
) : Parcelable

@Parcelize
data class Wind(
    @SerializedName("deg")
    var degree: Double,
    @SerializedName("gust")
    var gust: Double,
    @SerializedName("speed")
    var speed: Double
) : Parcelable

@Parcelize
data class Coordinate(
    @SerializedName("lat")
    var latitude: Double,
    @SerializedName("lon")
    var longitude: Double
) : Parcelable
