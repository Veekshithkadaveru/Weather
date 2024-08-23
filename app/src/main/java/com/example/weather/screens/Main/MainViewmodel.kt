package com.example.weather.screens.Main

import androidx.lifecycle.ViewModel
import com.example.weather.data.DataOrException
import com.example.weather.model.Weather
import com.example.weather.repository.WeatherRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewmodel @Inject constructor(private val repository: WeatherRepository) : ViewModel() {
    suspend fun getWeatherData(city: String):
            DataOrException<Weather, Boolean, Exception> {
        return repository.getWeather(cityQuery = city)
    }

}