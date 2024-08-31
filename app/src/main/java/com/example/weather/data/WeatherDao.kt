package com.example.weather.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.weather.model.Favorite
import com.example.weather.model.Unit
import kotlinx.coroutines.flow.Flow

@Dao
interface WeatherDao {
    @Query("SELECT * from fav_table")
    fun getFavorites(): Flow<List<Favorite>>

    @Query("SELECT * from fav_table WHERE city=:city")
    suspend fun getFavouriteById(city: String): Favorite

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavourite(favorite: Favorite)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateFavourite(favorite: Favorite)

    @Query("DELETE from fav_table")
    suspend fun deleteAllFavourites()

    @Delete
    suspend fun deleteFavorites(favorite: Favorite)

    //Unit table
    @Query("SELECT * from settings_tbl")
    fun getUnits(): Flow<List<Unit>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUnits(unit: Unit)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateUnit(unit: Unit)

    @Query("DELETE from settings_tbl")
    suspend fun deleteAllUnits()

    @Delete
    suspend fun deleteUnits(unit: Unit)
}