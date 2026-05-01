package com.vcyberpunk.weather.data.repository

import com.vcyberpunk.weather.data.mapper.toEntity
import com.vcyberpunk.weather.data.network.api.ApiService
import com.vcyberpunk.weather.domain.entity.Forecast
import com.vcyberpunk.weather.domain.entity.Weather
import com.vcyberpunk.weather.domain.repository.WeatherRepository
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(
    private val apiService: ApiService
) : WeatherRepository {
    override suspend fun getWeather(cityId: Int): Weather {
        return apiService.loadCurrentWeather("$PREFIX_CITY_ID$cityId").toEntity()
    }

    override suspend fun getForecast(cityId: Int): Forecast {
        return apiService.loadForecast("$PREFIX_CITY_ID$cityId").toEntity()
    }

    companion object {

        private const val PREFIX_CITY_ID = "id:"

    }
}