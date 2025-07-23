package com.younesbelouche.rickandmorty.data.data_sources.remote

import com.younesbelouche.rickandmorty.data.data_sources.remote.dto.CharacterDto
import com.younesbelouche.rickandmorty.data.data_sources.remote.dto.CharacterResponseDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CharactersApi {
    @GET("character")
    suspend fun getCharacters(
        @Query("page") page: Int,
        @Query("api_key") apiKey: String
    ): CharacterResponseDto

    @GET("character/{id}")
    suspend fun getCharacter(
        @Path("id") id: Int,
        @Query("api_key") apiKey: String
    ): CharacterDto
}