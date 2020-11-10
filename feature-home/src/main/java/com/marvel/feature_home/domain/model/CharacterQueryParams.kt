package com.marvel.feature_home.domain.model

data class CharacterQueryParams(var limit: Int, val offset: Int) {
    companion object {
        const val QUERY_LIMIT = "limit"
        const val QUERY_OFFSET = "offset"
    }

    fun toMap(): Map<String, String> {
        return mapOf(
            QUERY_LIMIT to "$limit",
            QUERY_OFFSET to "$offset"
        )
    }
}