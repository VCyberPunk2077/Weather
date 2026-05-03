package com.vcyberpunk.weather.di

import android.content.Context
import com.vcyberpunk.weather.data.local.db.FavouriteCitiesDao
import com.vcyberpunk.weather.data.local.db.FavouriteDatabase
import com.vcyberpunk.weather.data.network.api.ApiFactory
import com.vcyberpunk.weather.data.network.api.ApiService
import com.vcyberpunk.weather.data.repository.FavouriteRepositoryImpl
import com.vcyberpunk.weather.data.repository.SearchRepositoryImpl
import com.vcyberpunk.weather.data.repository.WeatherRepositoryImpl
import com.vcyberpunk.weather.domain.repository.FavouriteRepository
import com.vcyberpunk.weather.domain.repository.SearchRepository
import com.vcyberpunk.weather.domain.repository.WeatherRepository
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
interface DataModule {

    @[ApplicationScope Binds]
    fun bindFavouriteRepository(impl: FavouriteRepositoryImpl): FavouriteRepository

    @[ApplicationScope Binds]
    fun bindSearchRepository(impl: SearchRepositoryImpl): SearchRepository

    @[ApplicationScope Binds]
    fun bindWeatherRepository(impl: WeatherRepositoryImpl): WeatherRepository

    companion object {

        @[ApplicationScope Provides]
        fun provideApiService(): ApiService = ApiFactory.apiService

        @[ApplicationScope Provides]
        fun provideFavouriteDatabase(context: Context): FavouriteDatabase =
            FavouriteDatabase.getInstance(context)

        @[ApplicationScope Provides]
        fun provideFavouriteCitiesDao(database: FavouriteDatabase): FavouriteCitiesDao =
            database.favouriteCitiesDao()

    }

}