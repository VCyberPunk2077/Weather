package com.vcyberpunk.weather.presentation.search

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults.InputField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.vcyberpunk.weather.R
import com.vcyberpunk.weather.domain.entity.City

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchContent(component: SearchComponent) {
    val model by component.model.collectAsState()
    val focusRequester = remember {
        FocusRequester()
    }

    LaunchedEffect(key1 = Unit) {
        focusRequester.requestFocus()
    }

    BackHandler {
        component.onClickBack()
    }

    SearchBar(
        modifier = Modifier
            .fillMaxWidth()
            .focusRequester(focusRequester),
        inputField = {
            InputField(
                onSearch = {
                    component.onClickSearch()
                },
                placeholder = { Text(stringResource(R.string.search_placeholder)) },
                leadingIcon = {
                    IconButton(onClick = {
                        component.onClickBack()
                    }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = stringResource(R.string.icon_go_back_content_description)
                        )
                    }
                },
                trailingIcon = {
                    IconButton(onClick = {
                        component.onClickSearch()
                    }) {
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = stringResource(R.string.search_icon_content_description)
                        )
                    }
                },
                query = model.searchQuery,
                onQueryChange = { query -> component.onChangeSearchQuery(query = query) },
                expanded = true,
                onExpandedChange = { },
            )
        },
        expanded = true,
        onExpandedChange = {
            component.onClickBack()
        },
    ) {
        when (val searchState = model.searchState) {
            SearchStore.State.SearchState.EmptyResult -> {
                Text(
                    text = stringResource(R.string.empty_result_text),
                    modifier = Modifier.padding(8.dp)
                )
            }

            SearchStore.State.SearchState.Error -> {
                Text(
                    text = stringResource(R.string.error_search_result_text),
                    modifier = Modifier.padding(8.dp)
                )
            }

            SearchStore.State.SearchState.Initial -> {

            }

            SearchStore.State.SearchState.Loading -> {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator(
                        color = MaterialTheme.colorScheme.onBackground
                    )
                }
            }

            is SearchStore.State.SearchState.SuccessLoaded -> {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    contentPadding = PaddingValues(16.dp)
                ) {
                    items(items = searchState.cities, key = { it.id }) {
                        CityCard(it) { city ->
                            component.onClickCity(city)
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun CityCard(city: City, onCityClick: (City) -> Unit) {
    Card(
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .clickable(onClick = { onCityClick(city) })
                .padding(
                    vertical = 8.dp,
                    horizontal = 16.dp
                )
        ) {
            Text(
                text = city.name,
                style = MaterialTheme.typography.titleMedium
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = city.country
            )
        }
    }
}