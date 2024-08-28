package com.example.weather.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.weather.screens.Main.MainScreen
import com.example.weather.screens.Main.MainViewmodel
import com.example.weather.screens.Splash.WeatherSplashScreen
import com.example.weather.screens.about.AboutScreen
import com.example.weather.screens.favourites.FavoritesScreen
import com.example.weather.screens.search.SearchScreen
import com.example.weather.screens.settings.SettingsScreen

@Composable
fun WeatherNavigation() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = WeatherScreens.SplashScreen.name
    ) {
        composable(WeatherScreens.SplashScreen.name) {
            WeatherSplashScreen(navController = navController)
        }
        val route = WeatherScreens.MainScreen.name
        composable("$route/{city}",
            arguments = listOf(
                navArgument(name = "city") {
                    type = NavType.StringType
                }
            )) { navBack ->
            navBack.arguments?.getString("city").let { city ->
                val mainViewmodel = hiltViewModel<MainViewmodel>()
                MainScreen(
                    navController = navController,
                    mainViewmodel,
                    city = city
                )
            }

        }
        composable(WeatherScreens.SearchScreen.name) {
            SearchScreen(navController = navController)
        }
        composable(WeatherScreens.AboutScreen.name) {
           AboutScreen(navController = navController)
        }
        composable(WeatherScreens.FavouriteScreen.name) {
            FavoritesScreen(navController = navController)
        }
        composable(WeatherScreens.SettingsScreen.name) {
            SettingsScreen(navController = navController)
        }
    }
}