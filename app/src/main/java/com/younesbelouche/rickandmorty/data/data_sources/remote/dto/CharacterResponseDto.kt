package com.younesbelouche.rickandmorty.data.data_sources.remote.dto

data class CharacterResponseDto(
    val info: PageInfoDto,
    val results: List<CharacterDto>
)
