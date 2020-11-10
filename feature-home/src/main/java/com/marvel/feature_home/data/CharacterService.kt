package com.marvel.feature_home.data

import com.marvel.feature_common.model.data.CharacterResponse
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface CharacterService {
    @GET("characters")
    suspend fun getCharacters(@QueryMap options: Map<String, String>): CharacterResponse
}