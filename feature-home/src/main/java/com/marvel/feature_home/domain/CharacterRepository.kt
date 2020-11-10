package com.marvel.feature_home.domain

import com.marvel.feature_common.base.Result
import com.marvel.feature_common.model.domain.Character
import com.marvel.feature_home.domain.model.CharacterQueryParams
import kotlinx.coroutines.flow.Flow


interface CharacterRepository {
    suspend fun getCharacterStream(queryParams: CharacterQueryParams) : Flow<Result<List<Character>>>
    suspend fun requestMoreCharacters(queryParams: CharacterQueryParams)
}