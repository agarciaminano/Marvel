package com.alejandro.marvel.di.modules

import com.alejandro.marvel.di.qualifiers.IoDispatcher
import com.marvel.feature_home.domain.CharacterRepository
import com.marvel.feature_home.domain.GetCharactersPagingUseCase
import com.marvel.feature_home.domain.GetCharactersStreamUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import kotlinx.coroutines.CoroutineDispatcher

@Module
@InstallIn(ApplicationComponent::class)
object UseCaseModule {

    @Provides
    fun provideGetCharactersStreamUseCase(
        @IoDispatcher coroutineDispatcher: CoroutineDispatcher,
        characterRepository: CharacterRepository
    ): GetCharactersStreamUseCase = GetCharactersStreamUseCase(
        coroutineDispatcher = coroutineDispatcher,
        characterRepository = characterRepository
    )

    @Provides
    fun provideGetCharactersPagingUseCase(
        characterRepository: CharacterRepository
    ): GetCharactersPagingUseCase = GetCharactersPagingUseCase(characterRepository)

}