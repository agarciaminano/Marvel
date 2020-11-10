package com.marvel.feature_home.presentation.viewmodel.paginator

import com.marvel.feature_home.domain.model.CharacterQueryParams

class PaginatorHomeHandler(private var offset: Int, private val pageSize: Int) : Paginator {

    var paginatorState = PaginatorState.ENABLED
    private var hasMoreItems = true


    companion object {
        const val DEFAULT_OFFSET = 0
        const val DEFAULT_PAGE_SIZE = 20
    }

    override fun reset() {
        offset = 0
        hasMoreItems = true
    }

    private fun nextPage(itemCount: Int) {
        offset += itemCount
    }

    override fun onPageResult(itemCount: Int) {
        hasMoreItems = itemCount >= pageSize
        nextPage(itemCount)
    }

    fun getCharacterQueryParams() = CharacterQueryParams(pageSize, offset)

    fun hasMoreItems() = this.hasMoreItems

}