package com.vcyberpunk.weather.di

import android.content.Context
import com.vcyberpunk.weather.data.local.db.ApplicationScope
import dagger.BindsInstance
import dagger.Component

@ApplicationScope
@Component(modules = [
    DataModule::class
])
interface ApplicationComponent {

    @Component.Factory
    interface Factory {

        fun create(
            @BindsInstance context: Context
        ): ApplicationComponent

    }
}