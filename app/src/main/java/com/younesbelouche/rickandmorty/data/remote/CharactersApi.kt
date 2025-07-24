package com.younesbelouche.rickandmorty.data.remote

import com.younesbelouche.rickandmorty.core.Constants
import com.younesbelouche.rickandmorty.data.remote.dto.CharacterDto
import com.younesbelouche.rickandmorty.data.remote.dto.CharacterResponseDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CharactersApi {
    @GET("character")
    suspend fun getCharacters(
        @Query("page") page: Int,
        @Query("api_key") apiKey: String = Constants.API_KEY
    ): CharacterResponseDto

    @GET("character/{id}")
    suspend fun getCharacter(
        @Path("id") id: Int,
        @Query("api_key") apiKey: String = Constants.API_KEY
    ): CharacterDto
}