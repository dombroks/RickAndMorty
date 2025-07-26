package com.younesbelouche.rickandmorty.presentation.characterdetails

import com.younesbelouche.rickandmorty.presentation.characters.CharactersEvent

sealed class CharacterDetailsEvent {
    data class GetCharacter(val characterId: Int) : CharacterDetailsEvent()
}