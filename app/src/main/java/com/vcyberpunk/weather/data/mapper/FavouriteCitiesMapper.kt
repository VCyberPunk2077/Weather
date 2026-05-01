package com.vcyberpunk.weather.data.mapper

import com.vcyberpunk.weather.data.local.model.CityDbModel
import com.vcyberpunk.weather.domain.entity.City

fun City.toDbModel(): CityDbModel = CityDbModel(
    id = id,
    name = name,
    country = country
)

fun CityDbModel.toEntity(): City = City(
    id = id,
    name = name,
    country = country
)

fun List<CityDbModel>.toListEntity(): List<City> = this.map {
    it.toEntity()
}