package com.vcyberpunk.weather.domain.usecase

import com.vcyberpunk.weather.domain.repository.FavouriteRepository
import javax.inject.Inject

class GetFavouriteCitiesUseCase @Inject constructor(private val repository: FavouriteRepository) {

    operator fun invoke() = repository.favouriteCities

}