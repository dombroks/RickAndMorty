package com.younesbelouche.rickandmorty.presentation.characterdetails

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable

@Serializable
data class CharacterDetailsDestination(
    val characterId: Int
)


fun NavGraphBuilder.characterDetailsScreen(
    onNavigateBack: () -> Unit = {}
) {
    composable<CharacterDetailsDestination> {
        val characterId = it.arguments?.getInt("characterId")
            ?: -1
        val characterDetailsViewModel: CharacterDetailsViewModel = hiltViewModel()
        val state by characterDetailsViewModel.state.collectAsState()
        CharacterDetailsScreen(
            characterId = characterId,
            characterDetailsState = state,
            characterDetailsEvent = characterDetailsViewModel::onEvent,
            onNavigateBack = onNavigateBack
        )
    }
}

fun NavController.navigateToCharacterDetailsScreen(characterId: Int) {
    navigate(CharacterDetailsDestination(characterId))
}