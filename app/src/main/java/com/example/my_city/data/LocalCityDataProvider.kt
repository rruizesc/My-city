package com.example.my_city.data


import com.example.my_city.R
import com.example.my_city.model.City
import com.example.my_city.model.Opcion

object LocalCityDataProvider {

    private val subParques = listOf(
        Opcion(
            id = 1,
            titleResourceId = R.string.casaDeCampo,
            detailTextId = R.string.CasaDeCampo_detail_text,
            imageResourceId = R.drawable.casa_de_campo
        ),
        Opcion(
            id = 2,
            titleResourceId = R.string.Retiro,
            detailTextId = R.string.Retiro,
            imageResourceId = R.drawable.retiro
        ),
        Opcion(
            id = 3,
            titleResourceId = R.string.JuanCarlos1,
            detailTextId = R.string.JuanCarlos1_list_subtitle,
            imageResourceId = R.drawable.juan_carlos
        )
    )

    private val subCentros = listOf(
        Opcion(
            id = 1,
            titleResourceId = R.string.Islazul,
            detailTextId = R.string.Islazul_detail_text,
            imageResourceId = R.drawable.islazul
        ),
        Opcion(
            id = 2,
            titleResourceId = R.string.LaVaguada,
            detailTextId = R.string.LaVaguada_detail_text,
            imageResourceId = R.drawable.vaguada
        ),
        Opcion(
            id = 3,
            titleResourceId = R.string.LaGavia,
            detailTextId = R.string.LaGavia_detail_text,
            imageResourceId = R.drawable.gavia
        )
    )

    private val subApto = listOf(
        Opcion(
            id = 1,
            titleResourceId = R.string.RatonPerez,
            detailTextId = R.string.RatonPerez_detail_text,
            imageResourceId = R.drawable.raton
        ),
        Opcion(
            id = 2,
            titleResourceId = R.string.TourBernabeu,
            detailTextId = R.string.TourBernabeu_detail_text,
            imageResourceId = R.drawable.mejor_estdio_del_mundo
        ),
        Opcion(
            id = 3,
            titleResourceId = R.string.ParqueDeAtracciones,
            detailTextId = R.string.ParqueDeAtracciones,
            imageResourceId = R.drawable.parque_de_atracciones
        )
    )

    private val subRestaurantes = listOf(
        Opcion(
            id = 1,
            titleResourceId = R.string.MordidaBernabeu,
            detailTextId = R.string.MordidaBernabeo_list_subtitle,
            imageResourceId = R.drawable.la_mordida
        ),
        Opcion(
            id = 2,
            titleResourceId = R.string.LaEsquinaDelReal,
            detailTextId = R.string.LaEsquinaDelReal_detail_text,
            imageResourceId = R.drawable.la_esquina
        ),
        Opcion(
            id = 3,
            titleResourceId = R.string.Sakana,
            detailTextId = R.string.Sakana_detail_text,
            imageResourceId = R.drawable.sakana
        )
    )

    fun getCategories(): List<City>{
        return listOf(
            City(1,R.drawable.parque, R.string.parques, subParques),
            City(2,R.drawable.centro_comercial, R.string.centros, subCentros),
            City(3,R.drawable.parque_apto,R.string.niños, subApto),
            City(4,R.drawable.restaurante, R.string.restaurantes, subRestaurantes)
        )
    }

}