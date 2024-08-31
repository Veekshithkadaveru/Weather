package com.example.weather.screens.settings

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weather.model.Unit
import com.example.weather.repository.WeatherDbRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class SettingsViewmodel @Inject constructor(private val repository: WeatherDbRepository) :
    ViewModel() {
    private val _unitList = MutableStateFlow<List<Unit>>(emptyList())
    val unitList = _unitList.asStateFlow()


    init {
        viewModelScope.launch {
            repository.getUnits().distinctUntilChanged()
                .collect { listOfUnits ->
                    if (listOfUnits.isNullOrEmpty()) {
                        Log.d("TAG", ":Empty List")
                    } else {
                        _unitList.value = listOfUnits
                    }
                }
        }
    }

    fun insertUnit(unit: Unit) =
        viewModelScope.launch { repository.insertUnit(unit) }

    fun updateFavorite(unit: Unit) =
        viewModelScope.launch { repository.updateUnit(unit) }

    fun deleteFavorite(unit: Unit) =
        viewModelScope.launch { repository.deleteUnit(unit) }
}