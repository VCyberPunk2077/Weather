package com.vcyberpunk.weather.domain.entity

data class Forecast(
    val currentWeather: Weather,
    val upcoming: List<Weather>
)
