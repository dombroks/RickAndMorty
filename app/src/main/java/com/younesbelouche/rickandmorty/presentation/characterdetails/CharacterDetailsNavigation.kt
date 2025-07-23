package com.younesbelouche.rickandmorty.presentation.characterdetails

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
        CharacterDetailsScreen(
            characterId = characterId,
            onNavigateBack = onNavigateBack
        )
    }
}

fun NavController.navigateToCharacterDetailsScreen(characterId: Int) {
    navigate(CharacterDetailsDestination(characterId))
}