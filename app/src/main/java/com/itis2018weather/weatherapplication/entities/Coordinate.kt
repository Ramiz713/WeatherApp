package com.itis2018weather.weatherapplication.entities

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Coordinate(
    @SerializedName("lat")
    var latitude: Double,
    @SerializedName("lon")
    var longitude: Double
) : Parcelable
