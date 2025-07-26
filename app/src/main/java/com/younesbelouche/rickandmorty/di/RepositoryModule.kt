package com.younesbelouche.rickandmorty.di

import com.younesbelouche.rickandmorty.data.remote.CharactersApi
import com.younesbelouche.rickandmorty.data.repositories.CharactersRepositoryImpl
import com.younesbelouche.rickandmorty.domain.repositories.CharactersRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideNewsRepository(
        charactersApi: CharactersApi,
        @IoDispatcher dispatcher: CoroutineDispatcher
    ): CharactersRepository {
        return CharactersRepositoryImpl(charactersApi = charactersApi, dispatcher = dispatcher)
    }
}