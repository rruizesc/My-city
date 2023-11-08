package com.example.my_city.ui

import androidx.lifecycle.ViewModel
import com.example.my_city.data.LocalCityDataProvider
import com.example.my_city.model.City
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update


class MycityViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(
        CityUiState(
            CityList = LocalCityDataProvider.getCityData(),
            currentCity = LocalCityDataProvider.getCityData().getOrElse(0) {
                LocalCityDataProvider.defaultCity
            }
        )
    )
    val uiState: StateFlow<CityUiState> = _uiState

    fun updateCurrentCity(selectedCity: City) {
        _uiState.update {
            it.copy(currentCity = selectedCity)
        }
    }

    fun navigateToListPage() {
        _uiState.update {
            it.copy(isShowingListPage = true)
        }
    }


    fun navigateToDetailPage() {
        _uiState.update {
            it.copy(isShowingListPage = false)
        }
    }
}

data class CityUiState(
    val CityList: List<City> = emptyList(),
    val currentCity: City = LocalCityDataProvider.defaultCity,
    val isShowingListPage: Boolean = true
)
