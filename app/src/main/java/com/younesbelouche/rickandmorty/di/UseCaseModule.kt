package com.younesbelouche.rickandmorty.di

import com.younesbelouche.rickandmorty.domain.repositories.CharactersRepository
import com.younesbelouche.rickandmorty.domain.usecases.GetCharacterUseCase
import com.younesbelouche.rickandmorty.domain.usecases.GetCharactersUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Singleton
    @Provides
    fun provideGetCharactersUseCase(charactersRepository: CharactersRepository) =
        GetCharactersUseCase(charactersRepository = charactersRepository)

    @Singleton
    @Provides
    fun provideGetCharacterUseCase(charactersRepository: CharactersRepository) =
        GetCharacterUseCase(charactersRepository = charactersRepository)


}