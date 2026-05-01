package com.vcyberpunk.weather.data.network.api

import com.vcyberpunk.weather.data.network.dto.CityDto
import com.vcyberpunk.weather.data.network.dto.WeatherDto
import com.vcyberpunk.weather.data.network.dto.WeatherForecastDto
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("current.json")
    suspend fun loadCurrentWeather(
        @Query("q") query: String
    ): WeatherDto

    @GET("forecast.json")
    suspend fun loadForecast(
        @Query("q") query: String,
        @Query("days") daysCount: Int = 4
    ): WeatherForecastDto

    @GET("search.json")
    suspend fun searchCity(
        @Query("q") query: String,
    ): List<CityDto>


}