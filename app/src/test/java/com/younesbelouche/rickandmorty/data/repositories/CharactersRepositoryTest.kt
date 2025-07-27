package com.younesbelouche.rickandmorty.data.repositories

import androidx.paging.map
import app.cash.turbine.test
import com.google.common.truth.Truth
import com.younesbelouche.rickandmorty.characterList
import com.younesbelouche.rickandmorty.pageInfo
import com.younesbelouche.rickandmorty.data.remote.CharactersApi
import com.younesbelouche.rickandmorty.data.remote.dto.CharacterResponseDto
import com.younesbelouche.rickandmorty.data.remote.mapper.Mapper.toDomain
import com.younesbelouche.rickandmorty.domain.repositories.CharactersRepository
import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.junit4.MockKRule
import io.mockk.mockk
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class CharactersRepositoryTest {

    @get:Rule(order = 0)
    val mockkRule = MockKRule(this)

    private lateinit var sut: CharactersRepository

    private val charactersApi: CharactersApi = mockk()

    @OptIn(ExperimentalCoroutinesApi::class)
    private val dispatcher: CoroutineDispatcher = UnconfinedTestDispatcher()

    @Before
    fun setUp() {
        sut = CharactersRepositoryImpl(charactersApi, dispatcher)
    }

    @Test
    fun `getCharacters should return paging data`() = runTest {

        coEvery {
            charactersApi.getCharacters(any())
        } returns CharacterResponseDto(
            results = characterList,
            info = pageInfo
        )

        val result = sut.getCharacters()
        result.test {
            val firstItem = awaitItem()
            firstItem.map {
                Truth.assertThat(it).isEqualTo(characterList)
                awaitComplete()
            }

        }
    }

    @Test
    fun `getCharacter should return character by id`() = runTest {
        val characterId = 1
        val expectedCharacter = characterList.first()

        coEvery {
            charactersApi.getCharacter(characterId)
        } returns expectedCharacter

        val result = sut.getCharacter(characterId)
        result.test {
            val item = awaitItem()
            Truth.assertThat(item).isEqualTo(expectedCharacter.toDomain())
            awaitComplete()
        }
    }

    @Test
    fun `searchCharacter should return paging data for search query`() = runTest {
        val searchQuery = "Rick"
        coEvery {
            charactersApi.searchCharacter(searchQuery, any())
        } returns CharacterResponseDto(
            results = characterList,
            info = pageInfo
        )

        val result = sut.searchCharacter(searchQuery)
        result.test {
            val firstItem = awaitItem()
            firstItem.map {
                Truth.assertThat(it).isEqualTo(characterList)
                awaitComplete()
            }
        }
    }


    @After
    fun tearDown() {
        clearAllMocks()
    }

}