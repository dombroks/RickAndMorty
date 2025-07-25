package com.younesbelouche.rickandmorty.presentation.characters

sealed class CharactersEvent {
    data object GetCharacters : CharactersEvent()
    data class SearchByCharacterName(val characterName: String) : CharactersEvent()
}