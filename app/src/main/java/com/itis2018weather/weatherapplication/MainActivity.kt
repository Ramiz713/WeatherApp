package com.itis2018weather.weatherapplication

import android.Manifest
import android.annotation.SuppressLint
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
import com.itis2018weather.weatherapplication.adapter.WeatherAdapter
import com.itis2018weather.weatherapplication.database.WeatherDatabase
import io.reactivex.Completable
import kotlinx.android.synthetic.main.activity_main.*

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

    @SuppressLint("CheckResult")
    private fun submitWeatherList() {
        val db = WeatherDatabase.getInstance(this)
        val weatherDao = db?.weatherDao()
        val apiService = WeatherApiService.create(this)
        apiService.getWeatherOfNearCities(currentLatitude, currentLongtitude)
            .map { it.list }
            .doOnSuccess {
                weatherDao?.deleteAll()
                weatherDao?.insertAll(it)
            }
            .subscribeSingleOnIoObserveOnUi()
            .subscribe(
                { result ->
                    weatherList.addAll(result)
                    adapter.submitList(weatherList)
                },
                { error ->
                    Toast.makeText(this, error.message, Toast.LENGTH_LONG).show()
                    Completable.fromCallable {
                        weatherList.addAll(weatherDao?.getAll() ?: ArrayList())
                    }
                        .subscribeCompletableOnIoObserveOnUi()
                        .subscribe { adapter.submitList(weatherList) }
                }
            )
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
