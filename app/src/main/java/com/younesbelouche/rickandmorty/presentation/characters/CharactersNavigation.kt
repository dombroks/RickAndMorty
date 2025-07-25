package com.younesbelouche.rickandmorty.presentation.characters

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable


@Serializable
data object CharactersDestination

fun NavGraphBuilder.charactersScreen(
    onNavigateToCharacterDetails: (characterId : Int) -> Unit
) {
    composable<CharactersDestination> {
        val charactersViewModel : CharactersViewModel = hiltViewModel()
        val state by charactersViewModel.state.collectAsState()
        CharactersScreen(
            charactersState = state,
            charactersEvent = charactersViewModel::onEvent,
            onItemClick = onNavigateToCharacterDetails
        )
    }
}

fun NavController.navigateToCharactersScreen() {
    navigate(CharactersDestination)
}