package com.vcyberpunk.weather.presentation.root

import androidx.compose.runtime.Composable
import com.arkivanov.decompose.extensions.compose.stack.Children
import com.vcyberpunk.weather.presentation.details.DetailsContent
import com.vcyberpunk.weather.presentation.favourite.FavouriteContent
import com.vcyberpunk.weather.presentation.search.SearchContent
import com.vcyberpunk.weather.presentation.ui.theme.WeatherTheme

@Composable
fun RootContent(component: RootComponent) {
    WeatherTheme {
        Children(
            stack = component.stack,
        ) { child ->
            when (val instance = child.instance) {
                is RootComponent.Child.Details -> {
                    DetailsContent(component = instance.component)
                }

                is RootComponent.Child.Favourite -> {
                    FavouriteContent(component = instance.component)
                }

                is RootComponent.Child.Search -> {
                    SearchContent(component = instance.component)
                }
            }
        }
    }
}