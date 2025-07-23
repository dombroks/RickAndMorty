package com.younesbelouche.rickandmorty.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation


@Composable
fun NavGraph(
    startDestination: String
) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = startDestination) {
        navigation(
            route = Route.CharactersScreen.route,
            startDestination = Route.CharactersScreen.route
        ) {
            composable(
                route = Route.CharactersScreen.route
            ) {
                // Create a view for CharactersScreen
                // Create a composable for CharactersScreen
            }
        }
    }
}