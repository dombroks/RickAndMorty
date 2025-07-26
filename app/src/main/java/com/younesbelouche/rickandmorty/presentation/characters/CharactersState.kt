package com.younesbelouche.rickandmorty.presentation.characters

import androidx.paging.PagingData
import com.younesbelouche.rickandmorty.domain.entities.Character
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

data class CharactersState(
    val characters: Flow<PagingData<Character>> = emptyFlow(),
)
