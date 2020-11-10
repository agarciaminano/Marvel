package com.marvel.feature_home.presentation.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.marvel.feature_common.base.Result
import com.marvel.feature_common.base.ui.BaseViewModel
import com.marvel.feature_home.domain.GetCharactersPagingUseCase
import com.marvel.feature_home.domain.GetCharactersStreamUseCase
import com.marvel.feature_home.presentation.model.CharacterViewData
import com.marvel.feature_home.presentation.model.toCharacterListViewData
import com.marvel.feature_home.presentation.viewmodel.paginator.Paginator
import com.marvel.feature_home.presentation.viewmodel.paginator.PaginatorHomeHandler
import com.marvel.feature_home.presentation.viewmodel.paginator.PaginatorState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.math.abs


class HomeViewModel @ViewModelInject constructor(
    getCharactersStreamUseCase: GetCharactersStreamUseCase,
    private val getCharactersPagingUseCase: GetCharactersPagingUseCase,
    private val paginatorHomeHandler: PaginatorHomeHandler
) :
    BaseViewModel(),
    Paginator by paginatorHomeHandler {

    companion object {
        const val MIN_QUERY_DIFF = 2

    }

    private val _filteredCharacters = MutableLiveData<List<CharacterViewData>>()
    val filteredCharacters: LiveData<List<CharacterViewData>>
        get() = _filteredCharacters

    private val _queryString = MutableLiveData("")

    val charactersLiveData: LiveData<Result<List<CharacterViewData>>> =
        liveData {
            val characterFlow =
                getCharactersStreamUseCase(paginatorHomeHandler.getCharacterQueryParams()).map { result ->
                    handleResult(result) {
                        paginatorHomeHandler.onPageResult(it.size)
                        it.toCharacterListViewData()
                    }
                }
            characterFlow.collect {
                emit(it)
            }

        }


    fun listScrolled(totalItemCount: Int, visibleItemCount: Int, firstVisibleItemPosition: Int) {
        if (!isLoading() && paginatorHomeHandler.paginatorState == PaginatorState.ENABLED && paginatorHomeHandler.hasMoreItems()
        ) {
            if (visibleItemCount + firstVisibleItemPosition >= totalItemCount
                && firstVisibleItemPosition >= 0
            ) {
                viewModelScope.launch {
                    withContext(Dispatchers.IO) {
                        getCharactersPagingUseCase.invoke(paginatorHomeHandler.getCharacterQueryParams())
                    }
                }
            }
        }

    }

    fun onQueryTextChanged(query: String, characterItems: List<CharacterViewData>) {
        val queryChanged = abs(query.length - _queryString.value!!.length) >= MIN_QUERY_DIFF
        if (queryChanged) {
            _queryString.value = query

            if (query.isEmpty()) {
                paginatorHomeHandler.paginatorState = PaginatorState.ENABLED
                _filteredCharacters.value = listOf()
                paginatorHomeHandler.reset()
                viewModelScope.launch {
                    withContext(Dispatchers.IO) {
                        getCharactersPagingUseCase.invoke(paginatorHomeHandler.getCharacterQueryParams())
                    }
                }
            } else {
                paginatorHomeHandler.paginatorState = PaginatorState.DISABLED
                _filteredCharacters.value = filterCharacters(characterItems, query)
            }

        }
    }

    fun filterCharacters(items: List<CharacterViewData>, filter: String): List<CharacterViewData> {
        return items.filter {
            it.name.contains(other = filter, ignoreCase = true)
        }
    }

    private fun isLoading(): Boolean {
        return charactersLiveData.value is Result.Loading
    }

}

