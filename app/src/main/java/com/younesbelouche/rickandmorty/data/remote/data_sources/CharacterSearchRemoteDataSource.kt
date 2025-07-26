package com.younesbelouche.rickandmorty.data.remote.data_sources

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.younesbelouche.rickandmorty.data.remote.CharactersApi
import com.younesbelouche.rickandmorty.data.remote.dto.CharacterDto
import javax.inject.Inject

class CharacterSearchRemoteDataSource @Inject constructor(
    private val charactersApi: CharactersApi,
    private val characterName: String
) : PagingSource<Int, CharacterDto>() {
    override fun getRefreshKey(state: PagingState<Int, CharacterDto>): Int? {
        return state.anchorPosition?.let { position ->
            val anchorPage = state.closestPageToPosition(position)
            anchorPage?.prevKey?.plus(1) ?: position.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, CharacterDto> {
        val page = params.key ?: 1
        val prevKey = if (page == 1) null else page - 1
        val nextKey = page + 1
        return try {
            val response = charactersApi.searchCharacter(page = page, characterName = characterName)
            LoadResult.Page(
                data = response.results.distinctBy{it.name},
                nextKey = nextKey,
                prevKey = prevKey
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}