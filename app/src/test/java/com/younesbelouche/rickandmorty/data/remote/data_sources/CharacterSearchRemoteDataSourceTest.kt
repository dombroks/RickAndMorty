package com.younesbelouche.rickandmorty.data.remote.data_sources

import android.annotation.SuppressLint
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.paging.PagingSource
import com.google.common.truth.Truth
import com.younesbelouche.rickandmorty.characterList
import com.younesbelouche.rickandmorty.data.remote.CharactersApi
import com.younesbelouche.rickandmorty.data.remote.dto.CharacterResponseDto
import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.junit4.MockKRule
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class CharacterSearchRemoteDataSourceTest {

    @get:Rule(order = 0)
    val mockkRule = MockKRule(this)

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @OptIn(ExperimentalCoroutinesApi::class)
    private val testDispatcher = UnconfinedTestDispatcher()

    private var charactersApi: CharactersApi = mockk()

    private lateinit var sut: CharacterSearchRemoteDataSource

    @Before
    fun setup() {
        sut = CharacterSearchRemoteDataSource(
            characterName = "Rick",
            charactersApi = charactersApi,
            dispatcher = testDispatcher
        )
    }

    @SuppressLint("CheckResult")
    @Test
    fun `load method should return page with data`() = runTest {
        coEvery { charactersApi.searchCharacter(any()) } returns CharacterResponseDto(
            info = mockk(),
            results = characterList
        )

        val params = PagingSource.LoadParams.Refresh(
            key = 1,
            loadSize = 20,
            placeholdersEnabled = false
        )

        val result = sut.load(params)

        Truth.assertThat(result is PagingSource.LoadResult.Page)
        val pageResult = result as PagingSource.LoadResult.Page
        Truth.assertThat(pageResult.data).isNotEmpty()
    }

    @Test
    fun loadReturnError() = runTest {

        coEvery { charactersApi.getCharacter(1) } throws Exception("Error")

        val params = PagingSource.LoadParams.Refresh(1, 10, false)

        val result = sut.load(params)

        Truth.assertThat(result is PagingSource.LoadResult.Error)
    }


    @After
    fun tearDown() {
        clearAllMocks()
    }
}