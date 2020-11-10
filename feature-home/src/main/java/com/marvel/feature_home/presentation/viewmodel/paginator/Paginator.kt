package com.marvel.feature_home.presentation.viewmodel.paginator

interface Paginator {
    fun reset()
    fun onPageResult(itemCount: Int)
}