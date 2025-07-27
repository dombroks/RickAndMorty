package com.younesbelouche.rickandmorty.presentation

import androidx.paging.AsyncPagingDataDiffer
import androidx.paging.PagingData
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListUpdateCallback
import app.cash.turbine.test
import com.google.common.truth.Truth.assertThat
import com.younesbelouche.rickandmorty.characterList
import com.younesbelouche.rickandmorty.data.remote.mapper.Mapper.toDomain
import com.younesbelouche.rickandmorty.domain.entities.Character
import com.younesbelouche.rickandmorty.domain.repositories.CharactersRepository
import com.younesbelouche.rickandmorty.domain.usecases.GetCharactersUseCase
import com.younesbelouche.rickandmorty.domain.usecases.SearchCharacterUseCase
import com.younesbelouche.rickandmorty.presentation.characters.CharactersEvent
import com.younesbelouche.rickandmorty.presentation.characters.CharactersState
import com.younesbelouche.rickandmorty.presentation.characters.CharactersViewModel
import io.mockk.clearAllMocks
import io.mockk.every
import io.mockk.junit4.MockKRule
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test


class CharactersViewModelTest {

    @get:Rule(order = 0)
    val mockkRule = MockKRule(this)

    private lateinit var sut: CharactersViewModel

    private var getCharactersUseCase: GetCharactersUseCase = mockk()
    private var searchCharacterUseCase: SearchCharacterUseCase = mockk()

    private var charactersRepository: CharactersRepository = mockk()

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setUp() {
        val pagingData = PagingData.from(characterList.map { it.toDomain() })

        every { getCharactersUseCase() } returns flowOf(pagingData)
        every { searchCharacterUseCase(any()) } returns flowOf(pagingData)

        every { charactersRepository.getCharacters() } returns flowOf(pagingData)
        every { charactersRepository.searchCharacter(any()) } returns flowOf(pagingData)


        sut = CharactersViewModel(
            getCharactersUseCase = getCharactersUseCase,
            searchCharacterUseCase = searchCharacterUseCase
        )

        Dispatchers.setMain(StandardTestDispatcher())
    }

    @Test
    fun `initial state should be empty`() = runTest {

        sut.state.test {
            val item = awaitItem()
            val empty = CharactersState(
                characters = emptyFlow()
            )
            assertThat(item).isEqualTo(empty)
            cancelAndIgnoreRemainingEvents()
        }

    }


    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `getCharacters event should update state with characters`() = runTest {
        sut.onEvent(CharactersEvent.GetCharacters)
        val characters = characterList.map { it.toDomain() }

        val differ = AsyncPagingDataDiffer(
            diffCallback = TestDiffCallback<Character>(),
            updateCallback = TestListCallback(),
            workerDispatcher = StandardTestDispatcher()
        )

        sut.state.test {
            val item = awaitItem()
            assertThat(item.characters).isNotNull()
            differ.submitData(PagingData.from(characters))
            advanceUntilIdle()
            assertThat(characters).isEqualTo(differ.snapshot().items)
            cancelAndIgnoreRemainingEvents()
        }
    }


    @Test
    fun `searchByCharacterName event should update state with filtered characters`() = runTest {
        val searchName = "Rick"
        sut.onEvent(
            CharactersEvent.SearchByCharacterName(
                searchName
            )
        )
        sut.state.test {
            val item = awaitItem()
            assertThat(item.characters).isNotNull()
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `search then getCharacters should reset characters list`() = runTest {
        sut.onEvent(CharactersEvent.SearchByCharacterName("Rick"))
        sut.onEvent(CharactersEvent.GetCharacters)

        sut.state.test {
            val searchState = awaitItem()
            val restoredState = awaitItem()

            assertThat(searchState).isNotNull()
            assertThat(restoredState).isNotNull()
            cancelAndIgnoreRemainingEvents()
        }
    }


    @OptIn(ExperimentalCoroutinesApi::class)
    @After
    fun tearDown() {
        clearAllMocks()
        Dispatchers.resetMain()
    }

}

class TestListCallback : ListUpdateCallback {
    override fun onChanged(position: Int, count: Int, payload: Any?) {}
    override fun onMoved(fromPosition: Int, toPosition: Int) {}
    override fun onInserted(position: Int, count: Int) {}
    override fun onRemoved(position: Int, count: Int) {}
}

class TestDiffCallback<T : Any> : DiffUtil.ItemCallback<T>() {
    override fun areItemsTheSame(oldItem: T, newItem: T): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: T, newItem: T): Boolean {
        return oldItem == newItem
    }
}