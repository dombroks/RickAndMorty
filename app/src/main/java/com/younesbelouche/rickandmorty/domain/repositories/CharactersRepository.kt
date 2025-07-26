package com.younesbelouche.rickandmorty.domain.repositories

import androidx.paging.PagingData
import com.younesbelouche.rickandmorty.domain.entities.Character
import kotlinx.coroutines.flow.Flow

interface CharactersRepository {
    fun getCharacters(): Flow<PagingData<Character>>

    fun getCharacter(
        id: Int,
    ): Flow<Character>
}