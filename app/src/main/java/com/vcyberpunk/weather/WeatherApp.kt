package com.vcyberpunk.weather

import android.app.Application
import com.vcyberpunk.weather.di.ApplicationComponent
import com.vcyberpunk.weather.di.DaggerApplicationComponent

class WeatherApp: Application() {

    lateinit var applicationComponent: ApplicationComponent

    override fun onCreate() {
        super.onCreate()
        applicationComponent = DaggerApplicationComponent.factory().create(this)
    }
}