package com.example.my_city.ui

import androidx.lifecycle.ViewModel
import com.example.my_city.R
import com.example.my_city.data.LocalCityDataProvider
import com.example.my_city.model.City
import com.example.my_city.model.Opcion
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
    val cityActual: City,
    val opcionActual: Opcion,
    val isShowingListPage: Boolean = true
)

val cityCategories: List<City> = LocalCityDataProvider.getCategories().toList()

val cityInicial = cityCategories.firstOrNull() ?: City(
    id = 0,
    imageResourceId = R.drawable.parque,
    titleResourceId = R.string.list_fragment_label,
    categories = emptyList()
)
val opcionInicial = cityInicial.categories.firstOrNull() ?: Opcion(
    id = 0,
    imageResourceId = R.drawable.parque,
    titleResourceId = R.string.list_fragment_label,
    detailTextId = R.string.imagen_city
)

val estadoActual = CityUiState(
    cityActual = cityInicial,
    opcionActual = opcionInicial,
    isShowingListPage = true
)
