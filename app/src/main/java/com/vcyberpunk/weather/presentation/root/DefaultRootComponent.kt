package com.vcyberpunk.weather.presentation.root

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.DelicateDecomposeApi
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.router.stack.pop
import com.arkivanov.decompose.router.stack.push
import com.arkivanov.decompose.value.Value
import com.vcyberpunk.weather.domain.entity.City
import com.vcyberpunk.weather.presentation.details.DefaultDetailsComponent
import com.vcyberpunk.weather.presentation.favourite.DefaultFavouriteComponent
import com.vcyberpunk.weather.presentation.search.DefaultSearchComponent
import com.vcyberpunk.weather.presentation.search.OpenReason
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.serialization.Serializable

class DefaultRootComponent @AssistedInject constructor(
    private val detailsComponentFactory: DefaultDetailsComponent.Factory,
    private val favouriteComponentFactory: DefaultFavouriteComponent.Factory,
    private val searchComponentFactory: DefaultSearchComponent.Factory,
    @Assisted("componentContext") componentContext: ComponentContext
) : RootComponent, ComponentContext by componentContext {

    private val navigation = StackNavigation<Config>()

    override val stack: Value<ChildStack<*, RootComponent.Child>> = childStack(
        source = navigation,
        initialStack = { listOf(Config.Favourite) },
        handleBackButton = true,
        childFactory = ::child,
        serializer = Config.serializer(),
    )

    @OptIn(DelicateDecomposeApi::class)
    private fun child(
        config: Config,
        componentContext: ComponentContext
    ): RootComponent.Child {
        return when (config) {
            is Config.Details -> {
                val component = detailsComponentFactory.create(
                    city = config.city,
                    onBackClicked = {
                        navigation.pop()
                    },
                    componentContext = componentContext
                )
                RootComponent.Child.Details(component)
            }

            Config.Favourite -> {
                val component = favouriteComponentFactory.create(
                    componentContext = componentContext,
                    onCityItemClicked = { city ->
                        navigation.push(
                            configuration = Config.Details(city)
                        )
                    },
                    onSearchClicked = {
                        navigation.push(
                            configuration = Config.Search(openReason = OpenReason.AddToFavourite)
                        )
                    },
                    onAddToFavouriteClicked = {
                        navigation.push(
                            configuration = Config.Search(openReason = OpenReason.RegularSearch)
                        )
                    }
                )
                RootComponent.Child.Favourite(component)
            }

            is Config.Search -> {
                val component = searchComponentFactory.create(
                    componentContext = componentContext,
                    openReason = config.openReason,
                    onBackClicked = {
                        navigation.pop()
                    },
                    onCityClicked = { city ->
                        navigation.push(Config.Details(city))
                    },
                    onSavedToFavouriteClicked = {
                        navigation.pop()
                    },
                )
                RootComponent.Child.Search(component)
            }
        }
    }

    @Serializable
    private sealed interface Config {

        @Serializable
        data object Favourite : Config

        @Serializable
        data class Search(val openReason: OpenReason) : Config

        @Serializable
        data class Details(val city: City) : Config

    }

    @AssistedFactory
    interface Factory {

        fun create(
            @Assisted("componentContext") componentContext: ComponentContext
        ): DefaultRootComponent

    }
}