package com.itis2018weather.weatherapplication

import android.arch.persistence.room.Embedded
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.arch.persistence.room.TypeConverters
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import com.itis2018weather.weatherapplication.database.WeatherConverter
import kotlinx.android.parcel.Parcelize

data class WeatherList(
    var count: Int,
    var list: List<WeatherItem>,
    var message: String
)

@Parcelize
@Entity(tableName = "weather_data")
data class WeatherItem(
    @SerializedName("coord")
    @Embedded
    var coordinate: Coordinate,
    @SerializedName("dt")
    var dt: Int,
    @SerializedName("id")
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
    @SerializedName("weather")
    @TypeConverters(WeatherConverter::class)
    var weather: List<Weather>,
    @SerializedName("wind")
    @Embedded
    var wind: Wind
) : Parcelable {
    constructor() : this(Coordinate(), 0, 0, Forecast(), "", Country(), ArrayList(), Wind())
}

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
) : Parcelable {
    constructor() : this(0.0, 0.0, 0.0, 0.0, 0.0)
}

@Parcelize
data class Country(
    @SerializedName("country")
    var name: String
) : Parcelable {
    constructor() : this("")
}

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
) : Parcelable {
    constructor() : this(0.0, 0.0, 0.0)
}

@Parcelize
data class Coordinate(
    @SerializedName("lat")
    var latitude: Double,
    @SerializedName("lon")
    var longitude: Double
) : Parcelable {
    constructor() : this(0.0, 0.0)
}
