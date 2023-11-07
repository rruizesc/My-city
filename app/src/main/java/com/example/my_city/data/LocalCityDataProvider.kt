package com.example.my_city.data


import com.example.my_city.R
import com.example.my_city.model.City

object LocalCityDataProvider{
    val defaultSport = getSportsData()[0]

    fun getSportsData(): List<City> {
        return listOf(
            City(
                id = 1,
                titleResourceId = R.string.parques,
                subtitleResourceId = R.string.parques_list_subtitle,
                imageResourceId = R.drawable.ic_baseball_square,
                sportDetails = R.string.parques_detail_text
            ),
            City(
                id = 2,
                titleResourceId = R.string.centros,
                subtitleResourceId = R.string.centros_list_subtitle,
                imageResourceId = R.drawable.ic_badminton_square,
                sportDetails = R.string.centros_detail_text
            ),
            City(
                id = 3,
                titleResourceId = R.string.niños,
                subtitleResourceId = R.string.niños_list_subtitle,
                imageResourceId = R.drawable.ic_basketball_square,
                sportDetails = R.string.niños_detail_text
            ),
            City(
                id = 4,
                titleResourceId = R.string.restaurantes,
                subtitleResourceId = R.string.restaurantes_list_subtitle,
                imageResourceId = R.drawable.ic_bowling_square,
                sportDetails = R.string.restaurantes_detail_text
            ),
        )
    }
}
