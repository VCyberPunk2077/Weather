package com.vcyberpunk.weather.domain.repository

import com.vcyberpunk.weather.domain.entity.City

interface SearchRepository {

    suspend fun search(query: String): List<City>

}