package com.marvel.feature_home.domain

import com.marvel.feature_common.base.usecase.SingleUseCase
import com.marvel.feature_home.domain.model.CharacterQueryParams


class GetCharactersPagingUseCase(private val characterRepository: CharacterRepository) :
    SingleUseCase<CharacterQueryParams, Unit>() {

    override suspend fun execute(params: CharacterQueryParams) {
        characterRepository.requestMoreCharacters(params)
    }
}