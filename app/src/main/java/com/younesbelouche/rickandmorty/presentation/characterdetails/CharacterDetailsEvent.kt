package com.younesbelouche.rickandmorty.presentation.characterdetails

sealed class CharacterDetailsEvent {
    data class GetCharacter(val characterId: Int) : CharacterDetailsEvent()
}