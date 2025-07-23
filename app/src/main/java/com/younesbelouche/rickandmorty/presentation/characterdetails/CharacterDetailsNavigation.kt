package com.younesbelouche.rickandmorty.presentation.characterdetails

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable

@Serializable
data object CharacterDetailsDestination

fun NavGraphBuilder.characterDetailsScreen(
    onNavigateBack: () -> Unit = {}
) {
    composable<CharacterDetailsDestination> {
        CharacterDetailsScreen(
            characterId = 1,
            onNavigateBack = onNavigateBack
        )
    }
}

fun NavController.navigateToCharacterDetailsScreen() {
    navigate(CharacterDetailsDestination)
}