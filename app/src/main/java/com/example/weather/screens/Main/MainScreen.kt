package com.example.weather.screens.Main

import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.produceState
import androidx.navigation.NavController
import com.example.weather.data.DataOrException
import com.example.weather.model.Weather

@Composable
fun MainScreen(navController: NavController, mainViewmodel: MainViewmodel) {
    ShowData(mainViewmodel)
}

@Composable
fun ShowData(mainViewmodel: MainViewmodel) {

    val weatherData = produceState<DataOrException<Weather, Boolean, Exception>>(
        initialValue = DataOrException(loading = true)
    ) {
        value = mainViewmodel.getWeatherData(city = "Moscow")
    }.value

    if (weatherData.loading == true) {
        CircularProgressIndicator()
    } else if (weatherData.data != null) {
        Text(text = "Main Screen ${weatherData.data!!}")
    }


}