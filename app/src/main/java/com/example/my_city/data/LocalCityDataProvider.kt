package com.example.my_city.data


import com.example.my_city.R
import com.example.my_city.model.City
import com.example.my_city.model.SubCity

object LocalCityDataProvider {

    private val subParques = listOf(
        SubCity(
            R.drawable.parque,
            R.string.parques,
            LocalOpcionDataProvider.getOpcionDataForParques())
    )

    private val subCentros = listOf(
        SubCity(
            R.drawable.centro_comercial,
            R.string.centros,
            LocalOpcionDataProvider.getOpcionDataForCentros()
        )
    )

    private val subApto = listOf(
        SubCity(
            R.drawable.parque_apto,
            R.string.niños,
            LocalOpcionDataProvider.getOpcionDataForLugaresAptos()
        )
    )

    private val subRestaurantes = listOf(
        SubCity(
            R.drawable.restaurante,
            R.string.restaurantes,
            LocalOpcionDataProvider.getOpcionDataForRestaurantes()
        )
    )

    fun getCategories(): List<City>{
        return listOf(
            City(R.drawable.parque, R.string.parques, subParques),
            City(R.drawable.centro_comercial, R.string.centros, subCentros),
            City(R.drawable.parque_apto,R.string.niños, subApto),
            City(R.drawable.restaurante, R.string.restaurantes, subRestaurantes)
        )
    }

}