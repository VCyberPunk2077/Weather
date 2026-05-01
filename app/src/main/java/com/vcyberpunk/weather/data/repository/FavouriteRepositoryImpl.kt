package com.vcyberpunk.weather.data.repository

import com.vcyberpunk.weather.data.local.db.FavouriteCitiesDao
import com.vcyberpunk.weather.data.mapper.toDbModel
import com.vcyberpunk.weather.data.mapper.toListEntity
import com.vcyberpunk.weather.domain.entity.City
import com.vcyberpunk.weather.domain.repository.FavouriteRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class FavouriteRepositoryImpl @Inject constructor(
    private val dao: FavouriteCitiesDao,
) : FavouriteRepository {

    override val favouriteCities: Flow<List<City>> = dao.getFavouriteCities()
        .map { it.toListEntity() }

    override fun observeIsFavourite(cityId: Int): Flow<Boolean> = dao.observeIsFavourite(cityId)

    override suspend fun addToFavourite(city: City) {
        val dbModel = city.toDbModel()
        dao.addToFavourite(dbModel)
    }

    override suspend fun removeFromFavourite(cityId: Int) {
        dao.removeFromFavourite(cityId)
    }
}