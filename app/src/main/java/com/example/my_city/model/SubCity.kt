package com.example.my_city.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class SubCity(
    val id: Int,
    @StringRes val titleResourceId: Int,
    @StringRes val subtitleResourceId: Int,
    @StringRes val detailTextId: Int,
    @DrawableRes val imageResourceId: Int
) {
    constructor(id: Int, titleResourceId: Int, imageResourceId: Int) : this(
        id = id,
        titleResourceId = titleResourceId,
        subtitleResourceId = 0,
        detailTextId = 0,
        imageResourceId = imageResourceId
    )
}
