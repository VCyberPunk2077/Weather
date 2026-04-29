package com.vcyberpunk.weather.domain.usecase

import com.vcyberpunk.weather.domain.entity.City
import com.vcyberpunk.weather.domain.repository.FavouriteRepository
import javax.inject.Inject

class ChangeFavouriteStateUseCase @Inject constructor(private val repository: FavouriteRepository) {

    suspend fun addToFavourite(city: City) = repository.addToFavourite(city = city)

    suspend fun removeFromFavourite(cityId: Int) = repository.removeFromFavourite(cityId = cityId)

}