package com.younesbelouche.rickandmorty.data.remote

import com.younesbelouche.rickandmorty.data.remote.dto.CharacterDto
import com.younesbelouche.rickandmorty.data.remote.dto.CharacterResponseDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CharactersApi {
    @GET("character")
    suspend fun getCharacters(
        @Query("page") page: Int,
    ): CharacterResponseDto

    @GET("character/{id}")
    suspend fun getCharacter(
        @Path("id") id: Int,
    ): CharacterDto

    @GET("character")
    suspend fun searchCharacter(
        @Query("name") characterName: String,
        @Query("page") page: Int = 1,
    ): CharacterResponseDto
}