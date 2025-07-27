package com.younesbelouche.rickandmorty.presentation

import app.cash.turbine.test
import com.google.common.truth.Truth.assertThat
import com.younesbelouche.rickandmorty.characterList
import com.younesbelouche.rickandmorty.data.remote.mapper.Mapper.toDomain
import com.younesbelouche.rickandmorty.domain.repositories.CharactersRepository
import com.younesbelouche.rickandmorty.domain.usecases.GetCharacterUseCase
import com.younesbelouche.rickandmorty.presentation.characterdetails.CharacterDetailsEvent
import com.younesbelouche.rickandmorty.presentation.characterdetails.CharacterDetailsState
import com.younesbelouche.rickandmorty.presentation.characterdetails.CharacterDetailsViewModel
import io.mockk.clearAllMocks
import io.mockk.every
import io.mockk.junit4.MockKRule
import io.mockk.mockk
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test


class CharacterDetailsViewModelTest {

    @get:Rule(order = 0)
    val mockkRule = MockKRule(this)

    private lateinit var sut: CharacterDetailsViewModel

    private var getCharacterUseCase: GetCharacterUseCase = mockk()

    private var charactersRepository: CharactersRepository = mockk()

    @Before
    fun setUp() {
        val character = characterList.first().toDomain()

        every { getCharacterUseCase(any()) } returns flowOf(character)
        every { charactersRepository.getCharacter(any()) } returns flowOf(character)


        sut = CharacterDetailsViewModel(
            getCharacterUseCase = getCharacterUseCase
        )
    }

    @Test
    fun `initial state should be empty`() = runTest {

        sut.state.test {
            val item = awaitItem()
            val empty = CharacterDetailsState(
                character = emptyFlow()
            )
            assertThat(item).isEqualTo(empty)
            cancelAndIgnoreRemainingEvents()
        }

    }

    @Test
    fun `onEvent GetCharacter should update state with character`() = runTest {
        val characterId = 1
        val expectedCharacter = characterList.first().toDomain()

        sut.state.test {
            val initialState = awaitItem()

            sut.onEvent(CharacterDetailsEvent.GetCharacter(characterId))

            val updatedState = awaitItem()

            val character = updatedState.character.first()

            assertThat(character).isEqualTo(expectedCharacter)

            cancelAndIgnoreRemainingEvents()
        }
    }


    @After
    fun tearDown() {
        clearAllMocks()
    }

}
