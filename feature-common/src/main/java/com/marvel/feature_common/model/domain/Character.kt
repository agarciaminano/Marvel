package com.marvel.feature_common.model.domain

import com.marvel.feature_common.model.data.CharacterDto

data class Character(
    val id: Int,
    val name: String,
    val thumbnail: Image
)


data class Image(
    val path: String,
    val extension: String,
)