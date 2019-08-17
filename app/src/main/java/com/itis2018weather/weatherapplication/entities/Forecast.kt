package com.itis2018weather.weatherapplication.entities

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Forecast(
    var humidity: Double,
    var pressure: Double,
    @SerializedName("temp")
    var temperature: Double,
    @SerializedName("temp_max")
    var maxTemperature: Double,
    @SerializedName("temp_min")
    var minTemperature: Double
) : Parcelable
