package com.vcyberpunk.weather.data.repository

import com.vcyberpunk.weather.data.mapper.toListEntity
import com.vcyberpunk.weather.data.network.api.ApiService
import com.vcyberpunk.weather.domain.entity.City
import com.vcyberpunk.weather.domain.repository.SearchRepository
import javax.inject.Inject

class SearchRepositoryImpl @Inject constructor(
    private val apiService: ApiService
) : SearchRepository {
    override suspend fun search(query: String): List<City> =
        apiService.searchCity(query).toListEntity()
}