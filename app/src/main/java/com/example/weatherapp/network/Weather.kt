package com.example.weatherapp.network

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.squareup.moshi.Json

@JsonIgnoreProperties(ignoreUnknown = true)
data class Weather(
    val current: Current?,
    val forecast: Forecast?
)

@JsonIgnoreProperties(ignoreUnknown = true)
data class Forecast(
    val forecastday: List<NthDay>
)

//@JsonIgnoreProperties(ignoreUnknown = true)
//data class ForecastDay(
//    @Json(name = "0") val zero: NthDay?,
//    @Json(name = "1") val one: NthDay?,
//    @Json(name = "2") val two: NthDay?
//)

@JsonIgnoreProperties(ignoreUnknown = true)
data class NthDay (
val day: Day?
)

@JsonIgnoreProperties(ignoreUnknown = true)
data class Day(
    @Json(name = "avgtemp_c") val averageTmp: Double
)

@JsonIgnoreProperties(ignoreUnknown = true)
data class Current (
    @Json(name = "temp_c")
    val tempC: Double
)
