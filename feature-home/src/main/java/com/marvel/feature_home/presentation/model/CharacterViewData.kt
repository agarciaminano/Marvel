package com.marvel.feature_home.presentation.model

import com.marvel.feature_common.model.domain.Character
import com.marvel.feature_common.model.domain.Image


data class CharacterViewData(
    val id: Int,
    val name: String,
    val thumbnail: ImageData
)

fun Character.toCharacterViewData(): CharacterViewData =
    CharacterViewData(id = id, name = name, thumbnail = thumbnail.toImageData())

fun List<Character>.toCharacterListViewData(): List<CharacterViewData> =
    map { it.toCharacterViewData() }

data class ImageData(
    val path: String,
    val extension: String,
)

fun Image.toImageData(): ImageData = ImageData(path = path, extension = extension)