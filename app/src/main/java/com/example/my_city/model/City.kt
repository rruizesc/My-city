package com.example.my_city.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class City(
    val id: Int,
    @DrawableRes val imageResourceId: Int,
    @StringRes val titleResourceId: Int,
    val categories: List<Opcion>
)
