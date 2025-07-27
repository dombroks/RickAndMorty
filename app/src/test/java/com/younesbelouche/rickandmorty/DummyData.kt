package com.younesbelouche.rickandmorty

import com.younesbelouche.rickandmorty.data.remote.dto.CharacterDto
import com.younesbelouche.rickandmorty.data.remote.dto.LocationDto
import com.younesbelouche.rickandmorty.data.remote.dto.OriginDto
import com.younesbelouche.rickandmorty.data.remote.dto.PageInfoDto

val pageInfo = PageInfoDto(
    count = 100L,
    pages = 5L,
    next = "https://api.example.com/characters?page=2",
    prev = null
)


val characterList = listOf(
    CharacterDto(
        id = 1,
        name = "Rick Sanchez",
        status = "Alive",
        species = "Human",
        type = "",
        gender = "Male",
        originDto = OriginDto("Earth (C-137)", "https://rickandmortyapi.com/api/location/1"),
        location = LocationDto("Citadel of Ricks", "https://rickandmortyapi.com/api/location/3"),
        image = "https://rickandmortyapi.com/api/character/avatar/1.jpeg",
        episode = listOf(
            "https://rickandmortyapi.com/api/episode/1",
            "https://rickandmortyapi.com/api/episode/2"
        ),
        url = "https://rickandmortyapi.com/api/character/1",
        created = "2017-11-04T18:48:46.250Z"
    ),
    CharacterDto(
        id = 2,
        name = "Morty Smith",
        status = "Alive",
        species = "Human",
        type = "",
        gender = "Male",
        originDto = OriginDto("Earth (C-137)", "https://rickandmortyapi.com/api/location/1"),
        location = LocationDto("Citadel of Ricks", "https://rickandmortyapi.com/api/location/3"),
        image = "https://rickandmortyapi.com/api/character/avatar/2.jpeg",
        episode = listOf(
            "https://rickandmortyapi.com/api/episode/1",
            "https://rickandmortyapi.com/api/episode/2"
        ),
        url = "https://rickandmortyapi.com/api/character/2",
        created = "2017-11-04T18:50:21.651Z"
    ),
    CharacterDto(
        id = 3,
        name = "Summer Smith",
        status = "Alive",
        species = "Human",
        type = "",
        gender = "Female",
        originDto = OriginDto(
            "Earth (Replacement Dimension)",
            "https://rickandmortyapi.com/api/location/20"
        ),
        location = LocationDto(
            "Earth (Replacement Dimension)",
            "https://rickandmortyapi.com/api/location/20"
        ),
        image = "https://rickandmortyapi.com/api/character/avatar/3.jpeg",
        episode = listOf(
            "https://rickandmortyapi.com/api/episode/6",
            "https://rickandmortyapi.com/api/episode/7"
        ),
        url = "https://rickandmortyapi.com/api/character/3",
        created = "2017-11-04T19:09:56.428Z"
    )
)