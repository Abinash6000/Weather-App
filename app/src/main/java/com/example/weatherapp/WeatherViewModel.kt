package com.example.weatherapp

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.weatherapp.network.Weather
import com.example.weatherapp.network.WeatherApi
import kotlinx.coroutines.*

class WeatherViewModel : ViewModel() {

    private val _result = MutableLiveData<Deferred<Weather>>()
    val result: LiveData<Deferred<Weather>> get() = _result
    private val _city = MutableLiveData("Noida")
    val city: LiveData<String> get() = _city
    private val _temperature = MutableLiveData(27.0)
    val temperature: LiveData<Double> get() = _temperature

    fun setCityValue(text: String) {
        _city.value = text
    }

    fun loadWeatherData() {
        runBlocking {
            try {
                _result.value = async(Dispatchers.IO) { WeatherApi.retrofitService.getWeather(city.value.toString()) } // async is doing the api call asynchronously
                _temperature.value = result.value!!.await().current?.tempC
            } catch (e: Exception) {
                _city.value = e.toString()
            }
        }
    }
}