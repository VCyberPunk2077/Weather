package com.vcyberpunk.weather.data.mapper

import android.icu.util.Calendar
import com.vcyberpunk.weather.data.network.dto.WeatherDto
import com.vcyberpunk.weather.data.network.dto.WeatherForecastDto
import com.vcyberpunk.weather.domain.entity.Forecast
import com.vcyberpunk.weather.domain.entity.Weather
import java.util.Date

private const val MILLIS_IN_SECOND = 1000
private const val DEFAULT_ICON_RESOLUTION = "64x64"
private const val NEW_ICON_RESOLUTION = "128x128"
private const val VALUE_TO_DROP_FIRST_DAY = 1

fun WeatherDto.toEntity(): Weather = Weather(
    tempC = tempC,
    conditionText = condition.text,
    conditionUrl = condition.iconUrl.correctImageUrl(),
    date = date.toCalendar()
)

fun WeatherForecastDto.toEntity(): Forecast = Forecast(
    currentWeather = current.toEntity(),
    upcoming = forecast.forecast.drop(VALUE_TO_DROP_FIRST_DAY).map { dayDto ->
        val dayWeather = dayDto.dayWeather
        Weather(
            tempC = dayWeather.tempC,
            conditionText = dayWeather.condition.text,
            conditionUrl = dayWeather.condition.iconUrl.correctImageUrl(),
            date = dayDto.date.toCalendar()
        )
    }
)

private fun Long.toCalendar(): Calendar = Calendar.getInstance().apply {
    time = Date(this@toCalendar * MILLIS_IN_SECOND)
}

private fun String.correctImageUrl(): String = "https:$this".replace(
    oldValue = DEFAULT_ICON_RESOLUTION,
    newValue = NEW_ICON_RESOLUTION
)

