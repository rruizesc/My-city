package com.example.my_city.data

import com.example.my_city.R
import com.example.my_city.model.City
import com.example.my_city.model.SubCity
import com.example.my_city.model.SubCityCategory

object LocalCityDataProvider {
    val defaultCity = getCityData()[0]

    fun getCityData(): List<City> {
        return listOf(
            City(
                id = 1,
                titleResourceId = R.string.parques,
                subtitleResourceId = R.string.parques_list_subtitle,
                imageResourceId = R.drawable.parque,
                categories = listOf(
                    SubCityCategory(
                        categoryName = "Parques",
                        subCities = listOf(
                            SubCity(
                                id = 1,
                                titleResourceId = R.string.casaDeCampo,
                                subtitleResourceId = R.string.CaseDeCampo_list_subtitle,
                                detailTextId = R.string.CasaDeCampo_detail_text,
                                imageResourceId = R.drawable.casa_de_campo
                            ),
                            SubCity(
                                id = 2,
                                titleResourceId = R.string.Retiro,
                                subtitleResourceId = R.string.Retiro,
                                detailTextId = R.string.Retiro,
                                imageResourceId = R.drawable.retiro
                            ),
                            SubCity(
                                id = 3,
                                titleResourceId = R.string.JuanCarlos1,
                                subtitleResourceId = R.string.JuanCarlos1_list_subtitle,
                                detailTextId = R.string.JuanCarlos1_list_subtitle,
                                imageResourceId = R.drawable.juan_carlos
                            )
                        )
                    )
                )
            ),
            City(
                id = 2,
                titleResourceId = R.string.centros,
                subtitleResourceId = R.string.centros_list_subtitle,
                imageResourceId = R.drawable.centro_comercial,
                categories = listOf(
                    SubCityCategory(
                        categoryName = "Centros Comerciales",
                        subCities = listOf(
                            SubCity(
                                id = 1,
                                titleResourceId = R.string.Islazul,
                                subtitleResourceId = R.string.Islazul_list_subtitle,
                                detailTextId = R.string.Islazul_detail_text,
                                imageResourceId = R.drawable.islazul
                            ),
                            SubCity(
                                id = 2,
                                titleResourceId = R.string.LaVaguada,
                                subtitleResourceId = R.string.LaVaguada_list_subtitle,
                                detailTextId = R.string.LaVaguada_detail_text,
                                imageResourceId = R.drawable.vaguada
                            ),
                            SubCity(
                                id = 3,
                                titleResourceId = R.string.LaGavia,
                                subtitleResourceId = R.string.LaGavia_list_subtitle,
                                detailTextId = R.string.LaGavia_detail_text,
                                imageResourceId = R.drawable.gavia
                            )
                        )
                    )
                )
            ),
            City(
                id = 3,
                titleResourceId = R.string.niños,
                subtitleResourceId = R.string.niños_list_subtitle,
                imageResourceId = R.drawable.parque_apto,
                categories = listOf(
                    SubCityCategory(
                        categoryName = "Lugares aptos para niños",
                        subCities = listOf(
                            SubCity(
                                id = 1,
                                titleResourceId = R.string.RatonPerez,
                                subtitleResourceId = R.string.RatonPerez_list_subtitle,
                                detailTextId = R.string.RatonPerez_detail_text,
                                imageResourceId = R.drawable.raton
                            ),
                            SubCity(
                                id = 2,
                                titleResourceId = R.string.TourBernabeu,
                                subtitleResourceId = R.string.TourBernabeu_list_subtitle,
                                detailTextId = R.string.TourBernabeu_detail_text,
                                imageResourceId = R.drawable.mejor_estdio_del_mundo
                            ),
                            SubCity(
                                id = 3,
                                titleResourceId = R.string.ParqueDeAtracciones,
                                subtitleResourceId = R.string.ParqueDeAtracciones_list_subtitle,
                                detailTextId = R.string.parques_detail_text,
                                imageResourceId = R.drawable.parque_de_atracciones
                            )
                        )
                    )
                )
            ),
            City(
                id = 4,
                titleResourceId = R.string.restaurantes,
                subtitleResourceId = R.string.restaurantes_list_subtitle,
                imageResourceId = R.drawable.restaurante,
                categories = listOf(
                    SubCityCategory(
                        categoryName = "Restaurantes",
                        subCities = listOf(
                            SubCity(
                                id = 1,
                                titleResourceId = R.string.MordidaBernabeu,
                                subtitleResourceId = R.string.MordidaBernabeo_list_subtitle,
                                detailTextId = R.string.MordidaBernabeo_list_subtitle,
                                imageResourceId = R.drawable.la_mordida
                            ),
                            SubCity(
                                id = 2,
                                titleResourceId = R.string.LaEsquinaDelReal,
                                subtitleResourceId = R.string.LaEsquinaDelReal_list_subtitle,
                                detailTextId = R.string.LaEsquinaDelReal_detail_text,
                                imageResourceId = R.drawable.la_esquina
                            ),
                            SubCity(
                                id = 3,
                                titleResourceId = R.string.Sakana,
                                subtitleResourceId = R.string.Sakana_list_subtitle,
                                detailTextId = R.string.Sakana_detail_text,
                                imageResourceId = R.drawable.sakana
                            )
                        )
                    )
                )
            )
        )
    }
}
