package com.younesbelouche.rickandmorty.domain.usecases

import com.younesbelouche.rickandmorty.domain.repositories.CharactersRepository
import javax.inject.Inject

class SearchCharacterUseCase @Inject constructor(
    private val charactersRepository: CharactersRepository
) {
    operator fun invoke(characterName: String) =
        charactersRepository.searchCharacter(characterName = characterName)
}