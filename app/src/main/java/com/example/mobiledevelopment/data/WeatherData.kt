package com.example.mobiledevelopment.data

import java.util.concurrent.locks.Condition

data class WeatherData(
    val city: String,
    val time: String,
    val currentTemp: String,
    val condition: String,
    val icon: String,
    val maxTemp: String,
    val minTemp: String,
    val hours: String

)
