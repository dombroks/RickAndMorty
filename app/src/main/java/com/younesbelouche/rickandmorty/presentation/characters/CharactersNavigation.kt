package com.younesbelouche.rickandmorty.presentation.characters

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
        CharactersScreen(
            onItemClick = onNavigateToCharacterDetails
        )
    }
}

fun NavController.navigateToCharactersScreen() {
    navigate(CharactersDestination)
}