package com.younesbelouche.rickandmorty.domain

import app.cash.turbine.test
import com.google.common.truth.Truth.assertThat
import com.younesbelouche.rickandmorty.characterList
import com.younesbelouche.rickandmorty.data.remote.mapper.Mapper.toDomain
import com.younesbelouche.rickandmorty.domain.repositories.CharactersRepository
import com.younesbelouche.rickandmorty.domain.usecases.GetCharacterUseCase
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

class GetCharacterUseCaseTest {

    @get:Rule(order = 0)
    val mockkRule = MockKRule(this)

    private lateinit var sut: GetCharacterUseCase

    private var charactersRepository: CharactersRepository = mockk()

    @Before
    fun setUp() {
        sut = GetCharacterUseCase(charactersRepository)
    }

    @Test
    fun `invoking GetCharacterUseCase should return a character`() = runTest {
        val expectedItem = characterList.first().toDomain()

        every {
            charactersRepository.getCharacter(any())
        } returns flowOf(expectedItem)

        val result = sut(1)
        result.test {
            val firstItem = awaitItem()
            assertThat(firstItem).isEqualTo(expectedItem)
            awaitComplete()
        }
    }


    @After
    fun tearDown() {
        clearAllMocks()
    }


}