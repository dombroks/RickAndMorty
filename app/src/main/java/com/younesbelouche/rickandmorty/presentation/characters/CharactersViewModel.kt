package com.younesbelouche.rickandmorty.presentation.characters

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.younesbelouche.rickandmorty.domain.usecases.GetCharactersUseCase
import com.younesbelouche.rickandmorty.domain.usecases.SearchCharacterUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject


@HiltViewModel
class CharactersViewModel @Inject constructor(
    private val getCharactersUseCase: GetCharactersUseCase,
    private val searchCharacterUseCase: SearchCharacterUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(CharactersState())
    val state: StateFlow<CharactersState> = _state.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(),
        initialValue = CharactersState(),
    )

    fun onEvent(event: CharactersEvent) {
        when (event) {
            is CharactersEvent.GetCharacters -> {
                getCharacters()
            }

            is CharactersEvent.SearchByCharacterName -> {
                searchByCharacterName(event.characterName)
            }
        }
    }

    private fun searchByCharacterName(characterName: String) {
        val charactersFlow =
            searchCharacterUseCase(characterName = characterName).cachedIn(viewModelScope)
        _state.value = _state.value.copy(characters = charactersFlow)
    }

    private fun getCharacters() {
        val charactersFlow = getCharactersUseCase().cachedIn(viewModelScope)
        _state.value = _state.value.copy(characters = charactersFlow)
    }
}