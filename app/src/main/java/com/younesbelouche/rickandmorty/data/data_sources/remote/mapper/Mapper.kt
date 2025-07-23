package com.younesbelouche.rickandmorty.data.data_sources.remote.mapper

import com.younesbelouche.rickandmorty.data.data_sources.remote.dto.CharacterDto
import com.younesbelouche.rickandmorty.data.data_sources.remote.dto.LocationDto
import com.younesbelouche.rickandmorty.data.data_sources.remote.dto.OriginDto
import com.younesbelouche.rickandmorty.data.data_sources.remote.dto.PageInfoDto
import com.younesbelouche.rickandmorty.domain.entities.Location
import com.younesbelouche.rickandmorty.domain.entities.Origin
import com.younesbelouche.rickandmorty.domain.entities.PageInfo
import com.younesbelouche.rickandmorty.domain.entities.Character

object Mapper {
    fun CharacterDto.toDomain() = Character(
        id = id,
        name = name,
        status = status,
        species = species,
        type = type,
        gender = gender,
        origin = originDto.toDomain(),
        location = location.toDomain(),
        image = image,
        episode = episode,
        url = url,
        created = created
    )

    fun Character.toDto() = CharacterDto(
        id = id,
        name = name,
        status = status,
        species = species,
        type = type,
        gender = gender,
        originDto = origin.toDto(),
        location = location.toDto(),
        image = image,
        episode = episode,
        url = url,
        created = created
    )


    fun OriginDto.toDomain() = Origin(
        name = name,
        url = url
    )

    fun Origin.toDto() = OriginDto(
        name = name,
        url = url
    )


    fun LocationDto.toDomain() = Location(
        name = name,
        url = url
    )

    fun Location.toDto() = LocationDto(
        name = name,
        url = url
    )

    fun PageInfoDto.toDomain() = PageInfo(
        count = count,
        pages = pages,
        next = next,
        prev = prev
    )

    fun PageInfo.toDto() = PageInfoDto(
        count = count,
        pages = pages,
        next = next,
        prev = prev
    )
}