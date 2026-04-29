package com.vcyberpunk.weather.domain.repository

import com.vcyberpunk.weather.domain.entity.Forecast
import com.vcyberpunk.weather.domain.entity.Weather

interface WeatherRepository {

    suspend fun getWeather(cityId: Int): Weather

    suspend fun getForecast(cityId: Int): Forecast

}