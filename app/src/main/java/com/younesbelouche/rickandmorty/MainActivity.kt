package com.younesbelouche.rickandmorty

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.younesbelouche.rickandmorty.design_system.RickAndMortyTheme
import com.younesbelouche.rickandmorty.presentation.characterdetails.characterDetailsScreen
import com.younesbelouche.rickandmorty.presentation.characterdetails.navigateToCharacterDetailsScreen
import com.younesbelouche.rickandmorty.presentation.characters.CharactersDestination
import com.younesbelouche.rickandmorty.presentation.characters.charactersScreen
import com.younesbelouche.rickandmorty.presentation.characters.navigateToCharactersScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            RickAndMortyTheme {
                AppRoot()
            }
        }
    }
}

@Composable
fun AppRoot() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = CharactersDestination
    ) {
        charactersScreen(
            onNavigateToCharacterDetails = {
                navController.navigateToCharacterDetailsScreen()
            }
        )

        characterDetailsScreen(
            characterId = 1,
            onNavigateBack = {
                navController.navigateToCharactersScreen()
            }
        )
    }
}


