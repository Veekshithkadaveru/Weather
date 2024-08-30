package com.example.weather.repository

import com.example.weather.data.WeatherDao
import com.example.weather.model.Favorite
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class WeatherDbRepository @Inject constructor(private val weatherDao: WeatherDao) {
    fun getFavorites(): Flow<List<Favorite>> = weatherDao.getFavorites()
    suspend fun insertFavorite(favorite: Favorite) = weatherDao.insertFavourite(favorite)
    suspend fun updateFavorite(favorite: Favorite) = weatherDao.updateFavourite(favorite)
    suspend fun deleteAllFavorite(favorite: Favorite) = weatherDao.deleteAllFavourites()
    suspend fun deleteFavorite(favorite: Favorite) = weatherDao.deleteFavorites(favorite)
    suspend fun getFavById(city: String): Favorite = weatherDao.getFavouriteById(city)

}