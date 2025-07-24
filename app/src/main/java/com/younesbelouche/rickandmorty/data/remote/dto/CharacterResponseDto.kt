package com.younesbelouche.rickandmorty.data.remote.dto

data class CharacterResponseDto(
    val info: PageInfoDto,
    val results: List<CharacterDto>
)
