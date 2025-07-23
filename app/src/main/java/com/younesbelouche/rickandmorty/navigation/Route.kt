package com.younesbelouche.rickandmorty.navigation

sealed class Route(
    val route: String
) {
    data object CharactersScreen : Route(route = "charactersScreen")
    data object CharacterDetails : Route(route = "characterDetails")
}