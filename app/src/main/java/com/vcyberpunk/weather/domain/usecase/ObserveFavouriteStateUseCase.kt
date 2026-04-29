package com.vcyberpunk.weather.domain.usecase

import com.vcyberpunk.weather.domain.repository.FavouriteRepository
import javax.inject.Inject

class ObserveFavouriteStateUseCase @Inject constructor(private val repository: FavouriteRepository) {

    operator fun invoke(cityId: Int) = repository.observeIsFavourite(cityId = cityId)

}