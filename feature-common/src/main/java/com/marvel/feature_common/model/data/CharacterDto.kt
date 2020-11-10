package com.marvel.feature_common.model.data

import com.marvel.feature_common.model.domain.Character
import com.marvel.feature_common.model.domain.Image


data class CharacterResponse(val data: CharacterData)

data class CharacterData(
    val results: List<CharacterDto>
)

data class CharacterDto(
    val id: Int,
    val name: String,
    val thumbnail: ImageDto
) {
    fun toCharacter() = Character(id = id, name = name, thumbnail = thumbnail.toImage())

}

fun List<CharacterDto>.toCharacterList() = map { it.toCharacter() }

data class ImageDto(
    val path: String,
    val extension: String,
) {
    fun toImage() = Image(path = path, extension = extension)
}