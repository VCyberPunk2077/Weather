package com.vcyberpunk.weather.data.mapper

import com.vcyberpunk.weather.data.network.dto.CityDto
import com.vcyberpunk.weather.domain.entity.City

private fun CityDto.toEntity(): City = City(
    id = id,
    name = name,
    country = country
)

fun List<CityDto>.toListEntity(): List<City> {
    return this.map {
        it.toEntity()
    }
}

