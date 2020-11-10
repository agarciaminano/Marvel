package com.marvel.feature_home.data

import com.marvel.feature_common.model.domain.Character
import com.marvel.feature_home.domain.CharacterRepository
import com.marvel.feature_common.base.Result
import com.marvel.feature_common.model.data.CharacterDto
import com.marvel.feature_common.model.data.toCharacterList
import com.marvel.feature_home.data.CharacterPagingHandler.Companion.PAGE_SIZE
import com.marvel.feature_home.domain.model.CharacterQueryParams
import kotlinx.coroutines.channels.ConflatedBroadcastChannel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map


class CharacterRepositoryImpl(private val characterService: CharacterService) :
    CharacterRepository {

    companion object {
        const val QUERY_LIMIT = "limit"
        const val QUERY_OFFSET = "offset"

    }

    private val characterResult = ConflatedBroadcastChannel<Result<List<Character>>>()
    private val characterPagingHandler = CharacterPagingHandler()


    override suspend fun getCharacterStream(queryParams: CharacterQueryParams): Flow<Result<List<Character>>> {
        requestCharacters(buildCharacterQueryParams(queryParams))
        return characterResult.asFlow()
    }

    override suspend fun requestMoreCharacters(queryParams: CharacterQueryParams) {
        requestCharacters(buildCharacterQueryParams(queryParams))
    }

    private suspend fun requestCharacters(params: Map<String, String>) {
        characterResult.offer(Result.Loading(null))
        val data = characterService.getCharacters(params).data.results
        characterPagingHandler.onPageResult(data.size)
        characterResult.offer(Result.Success(data.toCharacterList()))
    }

    private fun buildCharacterQueryParams(queryParams: CharacterQueryParams): Map<String, String> = mapOf(
        QUERY_LIMIT to "${queryParams.limit}",
        QUERY_OFFSET to "${queryParams.offset}}"
    )


}