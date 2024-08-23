package com.example.weather.screens.Main

import android.annotation.SuppressLint
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.produceState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.weather.data.DataOrException
import com.example.weather.model.Weather
import com.example.weather.widgets.WeatherAppBar

@Composable
fun MainScreen(
    navController: NavController,
    mainViewmodel: MainViewmodel = hiltViewModel()
) {

    val weatherData = produceState<DataOrException<Weather, Boolean, Exception>>(
        initialValue = DataOrException(loading = true)
    ) {
        value = mainViewmodel.getWeatherData(city = "Moscow")
    }.value

    if (weatherData.loading == true) {
        CircularProgressIndicator()
    } else if (weatherData.data != null) {
        MainScaffold(weather = weatherData.data!!, navController)
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainScaffold(weather: Weather, navController: NavController) {
    Scaffold(topBar = {
        WeatherAppBar(title = "Helena, MT")
    }) {
        MainContent(data = weather)
    }
}

@Composable
fun MainContent(data: Weather) {
    Text(text = data.city.name)
}