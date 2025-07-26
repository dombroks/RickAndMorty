package com.younesbelouche.rickandmorty.data.repositories

import android.util.Log
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.filter
import androidx.paging.map
import com.younesbelouche.rickandmorty.core.Constants
import com.younesbelouche.rickandmorty.data.remote.CharactersApi
import com.younesbelouche.rickandmorty.data.remote.data_sources.CharacterSearchRemoteDataSource
import com.younesbelouche.rickandmorty.data.remote.data_sources.CharactersRemoteDataSource
import com.younesbelouche.rickandmorty.data.remote.mapper.Mapper.toDomain
import com.younesbelouche.rickandmorty.domain.entities.Character
import com.younesbelouche.rickandmorty.domain.repositories.CharactersRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class CharactersRepositoryImpl @Inject constructor(
    private val charactersApi: CharactersApi
) : CharactersRepository {
    override fun getCharacters(): Flow<PagingData<Character>> {
        return Pager(
            config = PagingConfig(
                pageSize = Constants.PAGE_SIZE,
            ),
            pagingSourceFactory = {
                CharactersRemoteDataSource(
                    charactersApi = charactersApi,
                )
            },
        ).flow
            .map { pagingData ->
                pagingData.map { characterDto ->
                    characterDto.toDomain()
                }
            }
    }

    override fun getCharacter(id: Int): Flow<Character> {
        return flow {
            emit(
                charactersApi.getCharacter(id = id).toDomain()
            )
        }
    }

    override fun searchCharacter(characterName: String): Flow<PagingData<Character>> {
        return Pager(
            config = PagingConfig(
                pageSize = Constants.PAGE_SIZE,
            ),
            pagingSourceFactory = {
                CharacterSearchRemoteDataSource(
                    charactersApi = charactersApi,
                    characterName = characterName
                )
            },
        ).flow
            .map { pagingData ->
                pagingData.map { characterDto ->
                    characterDto.toDomain()
                }
            }
    }
}