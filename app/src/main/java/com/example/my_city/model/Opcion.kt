package com.example.my_city.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Opcion(
    val id: Int,
    @DrawableRes val imageResourceId: Int,
    @StringRes val titleResourceId: Int,
    @StringRes val detailTextId: Int
)
