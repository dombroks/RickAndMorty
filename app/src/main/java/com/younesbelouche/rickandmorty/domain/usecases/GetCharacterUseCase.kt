package com.younesbelouche.rickandmorty.domain.usecases

import com.younesbelouche.rickandmorty.domain.repositories.CharactersRepository
import javax.inject.Inject

class GetCharacterUseCase @Inject constructor(
    private val charactersRepository : CharactersRepository
) {
    operator fun invoke(
        id: Int,
    ) = charactersRepository.getCharacter(
        id = id,
    )
}