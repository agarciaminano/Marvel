package com.marvel.feature_home.data

class CharacterPagingHandler {

    companion object {
        const val PAGE_SIZE = 20
        const val DEFAULT_OFFSET = 0

    }

    private var offset = DEFAULT_OFFSET
    private var hasMoreItems = true

    private fun nextPage(itemCount: Int) {
        offset += itemCount
    }

    fun reset() {
        hasMoreItems = true
        offset = 0
    }

    fun getCurrentOffset() = offset

    fun onPageResult(itemCount: Int) {
        hasMoreItems = itemCount < PAGE_SIZE
        nextPage(itemCount)
    }
}