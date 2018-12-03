package com.itis2018weather.weatherapplication

import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApiService {
    @GET("find?")
    fun getWeatherOfNearCities(
        @Query("lat") latitude: String,
        @Query("lon") longitude: String
    ): Call<WeatherList>

    companion object ApiFactory {
        private const val API_KEY = "77162a45807634e649058bebdd63141f"
        fun create(): WeatherApiService {
            val client = OkHttpClient.Builder()
                .addInterceptor {
                    val url = it.request()
                        .url()
                        .newBuilder()
                        .addQueryParameter("appid", API_KEY)
                        .addQueryParameter("units", "metric")
                        .addQueryParameter("cnt", "20")
                        .build()
                    val request = it.request().newBuilder().url(url).build()
                    it.proceed(request)
                }.build()
            return Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("http://api.openweathermap.org/data/2.5/")
                .client(client)
                .build()
                .create(WeatherApiService::class.java)
        }
    }
}
