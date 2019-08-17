package com.itis2018weather.weatherapplication.entities

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Wind(
    @SerializedName("deg")
    var degree: Double,
    var gust: Double,
    var speed: Double
) : Parcelable
