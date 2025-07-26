package com.younesbelouche.rickandmorty.presentation.characterdetails

import com.younesbelouche.rickandmorty.domain.entities.Character
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

data class CharacterDetailsState(
    val character : Flow<Character> = emptyFlow()
)
