package com.example.weather.screens.Main

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weather.data.DataOrException
import com.example.weather.model.WeatherObject
import com.example.weather.repository.WeatherRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewmodel @Inject constructor(private val repository: WeatherRepository) : ViewModel() {
    val data: MutableState<DataOrException<WeatherObject, Boolean, Exception>> =
        mutableStateOf(
            DataOrException(null, true, Exception(""))
        )

    init {
        loadWeather()
    }

    private fun loadWeather() {
        getWeather("Seattle")
    }

    private fun getWeather(city: String) {
        viewModelScope.launch {
            if (city.isEmpty()) return@launch
            data.value.loading = true
            data.value = repository.getWeather(cityQuery = city)
            if (data.value.data.toString().isNotEmpty()) data.value.loading = false
        }
        Log.d("TAG", "getWeather:${data.value.toString()}")

    }
}