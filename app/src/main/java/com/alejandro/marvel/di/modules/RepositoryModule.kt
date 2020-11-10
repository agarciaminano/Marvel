package com.alejandro.marvel.di.modules

import com.marvel.feature_home.data.CharacterRepositoryImpl
import com.marvel.feature_home.data.CharacterService
import com.marvel.feature_home.domain.CharacterRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideCharacterRepository(characterService: CharacterService): CharacterRepository {
        return CharacterRepositoryImpl(characterService)
    }

}