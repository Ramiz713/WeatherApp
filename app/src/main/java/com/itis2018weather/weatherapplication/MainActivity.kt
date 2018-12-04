package com.itis2018weather.weatherapplication

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.widget.Toast
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class MainActivity : AppCompatActivity() {
    private val PERMISSION_REQUEST_CODE = 1
    private lateinit var weatherList: ArrayList<WeatherItem>
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private var currentLatitude = "34.052235"
    private var currentLongtitude = "-118.243683"

    private val adapter = WeatherAdapter { position: Int ->
        val intent = Intent(this, WeatherInfoActivity::class.java)
        intent.putExtra("Weather item", weatherList[position])
        startActivity(intent)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
        weatherList = ArrayList()
        rv_cities.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        rv_cities.adapter = adapter

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_CALENDAR)
            != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.ACCESS_COARSE_LOCATION),
                PERMISSION_REQUEST_CODE
            )
        } else getCurrentLocation()
    }

    private fun getCurrentLocation() =
        fusedLocationClient.lastLocation.addOnSuccessListener {
            currentLongtitude = it.longitude.toString()
            currentLatitude = it.latitude.toString()
            submitWeatherList()
        }

    fun submitWeatherList() {
        val apiService = WeatherApiService.create()
        apiService.getWeatherOfNearCities(currentLatitude, currentLongtitude)
            .enqueue(object : Callback<WeatherList> {
                override fun onResponse(call: Call<WeatherList>?, response: Response<WeatherList>?) {
                    if (response != null && response.isSuccessful && response.body() != null) {
                        weatherList.addAll(response.body()?.list ?: ArrayList())
                        adapter.submitList(weatherList)
                    }
                }
                override fun onFailure(call: Call<WeatherList>?, t: Throwable?) {
                    Toast.makeText(this@MainActivity, t.toString(), Toast.LENGTH_LONG).show()
                }
            })
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        when (requestCode) {
            PERMISSION_REQUEST_CODE ->
                if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED))
                    getCurrentLocation()
                else submitWeatherList()
        }
    }
}
