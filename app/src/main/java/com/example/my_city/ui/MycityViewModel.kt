package com.example.my_city.ui

import androidx.lifecycle.ViewModel
import com.example.my_city.data.LocalCityDataProvider
import com.example.my_city.model.City
import com.example.my_city.model.Opcion
import com.example.my_city.model.SubCity
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update


class MycityViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(estadoActual)

    val uiState: StateFlow<CityUiState> = _uiState

    fun updateCurrentOpcion(
        opcionSeleccionada: Opcion
    ){
        _uiState.update {
            it.copy(
                opcionActual = opcionSeleccionada
            )
        }
    }

    fun updateCurrentSubCity(
        subCity: SubCity
    ) {
        _uiState.update {
            it.copy(
                subCityActual = subCity,
                opcionActual = subCity.opcion[0],
                isShowingListPage = true
            )
        }
    }

    fun navegarAListaOpciones() {
        _uiState.update {
            it.copy(
                isShowingListPage = true
            )
        }
    }

    fun navegarADetallesOpcion() {
        _uiState.update {
            it.copy(
                isShowingListPage = false
            )
        }
    }
}


data class CityUiState(
    val subCityActual: SubCity,
    val opcionActual: Opcion,
    val isShowingListPage: Boolean = true
)

val cityInicial = LocalCityDataProvider.getCategories()[0]
val estadoActual = CityUiState(
    subCityActual = cityInicial.categories[0],
    opcionActual = cityInicial.categories[0].opcion[0]

)