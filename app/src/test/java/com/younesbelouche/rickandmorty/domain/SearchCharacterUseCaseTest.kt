package com.younesbelouche.rickandmorty.domain

import androidx.paging.PagingData
import app.cash.turbine.test
import com.google.common.truth.Truth.assertThat
import com.younesbelouche.rickandmorty.characterList
import com.younesbelouche.rickandmorty.data.remote.mapper.Mapper.toDomain
import com.younesbelouche.rickandmorty.domain.repositories.CharactersRepository
import com.younesbelouche.rickandmorty.domain.usecases.SearchCharacterUseCase
import io.mockk.clearAllMocks
import io.mockk.every
import io.mockk.junit4.MockKRule
import io.mockk.mockk
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class SearchCharacterUseCaseTest {

    @get:Rule(order = 0)
    val mockkRule = MockKRule(this)

    private lateinit var sut: SearchCharacterUseCase

    private var charactersRepository: CharactersRepository = mockk()

    @Before
    fun setUp() {
        sut = SearchCharacterUseCase(charactersRepository)
    }

    @Test
    fun `invoking SearchCharacterUseCase should return paging data`() = runTest {
        val pagingData = PagingData.from(characterList.map { it.toDomain() })

        every {
            charactersRepository.searchCharacter(any())
        } returns flowOf(pagingData)

        val result = sut(
            characterName = "Rick Sanchez"
        )
        result.test {
            val firstItem = awaitItem()
            assertThat(firstItem).isEqualTo(pagingData)
            awaitComplete()
        }
    }


    @After
    fun tearDown() {
        clearAllMocks()
    }


}