package com.example.my_city.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class City(
    @DrawableRes val imageResourceId: Int,
    @StringRes val titleResourceId: Int,
    val categories: List<SubCity>
)
