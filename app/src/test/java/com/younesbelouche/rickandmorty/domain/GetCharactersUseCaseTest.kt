package com.younesbelouche.rickandmorty.domain

import androidx.paging.PagingData
import app.cash.turbine.test
import com.google.common.truth.Truth.assertThat
import com.younesbelouche.rickandmorty.characterList
import com.younesbelouche.rickandmorty.data.remote.mapper.Mapper.toDomain
import com.younesbelouche.rickandmorty.domain.repositories.CharactersRepository
import com.younesbelouche.rickandmorty.domain.usecases.GetCharactersUseCase
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

class GetCharactersUseCaseTest {

    @get:Rule(order = 0)
    val mockkRule = MockKRule(this)

    private lateinit var sut: GetCharactersUseCase

    private var charactersRepository: CharactersRepository = mockk()

    @Before
    fun setUp() {
        sut = GetCharactersUseCase(charactersRepository)
    }

    @Test
    fun `invoking GetCharactersUseCase should return paging data`() = runTest {
        val pagingData = PagingData.from(characterList.map { it.toDomain() })

        every {
            charactersRepository.getCharacters()
        } returns flowOf(pagingData)

        val result = sut()
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