package com.itis2018weather.weatherapplication

import android.content.Context
import io.reactivex.Single
import okhttp3.OkHttpClient
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
    ): Single<WeatherList>

    companion object ApiFactory {
        private const val API_DEFAULT_CITIES_COUNT = "20"
        private const val API_UNIT_METRIC = "metric"

        fun create(context: Context): WeatherApiService = Retrofit.Builder()
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BuildConfig.API_BASE_URL)
            .client(buildClient(context))
            .build()
            .create(WeatherApiService::class.java)

        private fun buildClient(context: Context): OkHttpClient {
            return OkHttpClient.Builder()
                .addInterceptor {
                    val url = it.request()
                        .url()
                        .newBuilder()
                        .addQueryParameter("appid", BuildConfig.API_KEY)
                        .addQueryParameter("units", API_UNIT_METRIC)
                        .addQueryParameter("cnt", API_DEFAULT_CITIES_COUNT)
                        .addQueryParameter("lang", context.getString(R.string.country_code))
                        .build()
                    val request = it.request().newBuilder().url(url).build()
                    it.proceed(request)
                }
                .build()
        }
    }
}
