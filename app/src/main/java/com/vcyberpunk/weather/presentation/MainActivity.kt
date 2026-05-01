package com.vcyberpunk.weather.presentation

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.vcyberpunk.weather.data.network.api.ApiFactory
import com.vcyberpunk.weather.presentation.ui.theme.WeatherTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val apiService = ApiFactory.apiService
        CoroutineScope(Dispatchers.Main).launch {
            Log.d("TEST_API_SERVICE", "${apiService.loadCurrentWeather("Kondopoga")}")
            Log.d("TEST_API_SERVICE", "${apiService.loadForecast("Kondopoga")}")
            Log.d("TEST_API_SERVICE", "${apiService.searchCity("London")}")
        }

        setContent {
            WeatherTheme {

            }
        }
    }
}