package com.younesbelouche.rickandmorty.domain.usecases

import com.younesbelouche.rickandmorty.domain.repositories.CharactersRepository
import javax.inject.Inject

class GetCharactersUseCase @Inject constructor(
    private val charactersRepository : CharactersRepository
) {
    operator fun invoke(
        page: Int,
    ) = charactersRepository.getCharacters(
        page = page,
    )
}