package com.example.weather.repository

import com.example.weather.data.DataOrException
import com.example.weather.model.WeatherObject
import com.example.weather.network.WeatherApi
import javax.inject.Inject

class WeatherRepository @Inject constructor(private val api: WeatherApi) {
    suspend fun getWeather(cityQuery: String)
            : DataOrException<WeatherObject, Boolean, Exception> {
        val response = try {
            api.getWeather(query = cityQuery)
        } catch (e: Exception) {
            return DataOrException(e = e)
        }
        return DataOrException(data = response)
    }
}