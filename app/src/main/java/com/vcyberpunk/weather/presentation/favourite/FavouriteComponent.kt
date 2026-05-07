package com.vcyberpunk.weather.presentation.favourite

import com.vcyberpunk.weather.domain.entity.City
import kotlinx.coroutines.flow.StateFlow

interface FavouriteComponent {

    val model: StateFlow<FavouriteStore.State>

    fun onClickSearch()

    fun onCityItemClick(city: City)

    fun onClickAddFavourite()

}