package com.marvel.feature_home.domain

import com.marvel.feature_common.base.Result
import com.marvel.feature_common.model.domain.Character
import com.marvel.feature_home.domain.model.CharacterQueryParams
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn


class GetCharactersStreamUseCase(
    private val characterRepository: CharacterRepository,
    private val coroutineDispatcher: CoroutineDispatcher
)  {

    @ExperimentalCoroutinesApi
    suspend operator fun invoke(queryParams: CharacterQueryParams) : Flow<Result<List<Character>>> {
        return getCharacterStream(queryParams)
    }

    @ExperimentalCoroutinesApi
    private suspend fun getCharacterStream(queryParams: CharacterQueryParams) : Flow<Result<List<Character>>> {
        return characterRepository.getCharacterStream(queryParams).flowOn(coroutineDispatcher)
    }



}