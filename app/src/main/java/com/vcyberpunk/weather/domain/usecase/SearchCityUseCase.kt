package com.vcyberpunk.weather.domain.usecase

import com.vcyberpunk.weather.domain.repository.SearchRepository
import javax.inject.Inject

class SearchCityUseCase @Inject constructor(private val repository: SearchRepository) {

    suspend operator fun invoke(query: String) = repository.search(query)

}