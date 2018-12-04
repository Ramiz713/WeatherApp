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
        fun create(): WeatherApiService = Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BuildConfig.API_BASE_URL)
            .client(buildClient())
                .build()
                .create(WeatherApiService::class.java)

        private fun buildClient(): OkHttpClient = OkHttpClient.Builder()
            .addInterceptor {
                val url = it.request()
                    .url()
                    .newBuilder()
                    .addQueryParameter("appid", BuildConfig.API_KEY)
                    .addQueryParameter("units", BuildConfig.API_UNIT_METRIC)
                    .addQueryParameter("cnt", BuildConfig.API_DEFAULT_CITIES_COUNT)
                    .build()
                val request = it.request().newBuilder().url(url).build()
                it.proceed(request)
            }.build()
    }
}
