package com.vcyberpunk.weather.presentation.root

import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.value.Value
import com.vcyberpunk.weather.presentation.details.DetailsComponent
import com.vcyberpunk.weather.presentation.favourite.FavouriteComponent
import com.vcyberpunk.weather.presentation.search.SearchComponent

interface RootComponent {

    val stack: Value<ChildStack<*, Child>>

    sealed interface Child {

        data class Favourite(val component: FavouriteComponent) : Child
        data class Search(val component: SearchComponent) : Child
        data class Details(val component: DetailsComponent) : Child

    }

}