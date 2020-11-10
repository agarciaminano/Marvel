package com.alejandro.marvel.di.modules

import com.marvel.feature_home.presentation.viewmodel.paginator.PaginatorHomeHandler
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent

@Module
@InstallIn(ActivityRetainedComponent::class)
object ViewModelModule {

    @Provides
    fun provideHomePaginator() = PaginatorHomeHandler(
        offset = PaginatorHomeHandler.DEFAULT_OFFSET,
        pageSize = PaginatorHomeHandler.DEFAULT_PAGE_SIZE
    )
}