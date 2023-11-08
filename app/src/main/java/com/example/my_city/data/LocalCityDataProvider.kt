package com.example.my_city.data


import com.example.my_city.R
import com.example.my_city.model.City

object LocalCityDataProvider{
    val defaultCity = getCityData()[0]

    fun getCityData(): List<City> {
        return listOf(
            City(
                id = 1,
                titleResourceId = R.string.parques,
                subtitleResourceId = R.string.parques_list_subtitle,
                imageResourceId = R.drawable.parque,
                cityDetails = R.string.parques_detail_text
            ),
            City(
                id = 2,
                titleResourceId = R.string.centros,
                subtitleResourceId = R.string.centros_list_subtitle,
                imageResourceId = R.drawable.centro_comercial,
                cityDetails = R.string.centros_detail_text
            ),
            City(
                id = 3,
                titleResourceId = R.string.niños,
                subtitleResourceId = R.string.niños_list_subtitle,
                imageResourceId = R.drawable.parque_apto,
                cityDetails = R.string.niños_detail_text
            ),
            City(
                id = 4,
                titleResourceId = R.string.restaurantes,
                subtitleResourceId = R.string.restaurantes_list_subtitle,
                imageResourceId = R.drawable.restaurante,
                cityDetails = R.string.restaurantes_detail_text
            ),
        )
    }
}
