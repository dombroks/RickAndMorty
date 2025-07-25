package com.younesbelouche.rickandmorty.presentation.characters

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.paging.compose.collectAsLazyPagingItems


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CharactersScreen(
    charactersState: CharactersState,
    charactersEvent: (CharactersEvent) -> Unit,
    onItemClick: (characterId: Int) -> Unit
) {

    LaunchedEffect(Unit) {
        charactersEvent(CharactersEvent.GetCharacters)
    }

    val characters = charactersState.characters.collectAsLazyPagingItems()

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(text = "Characters Screen") },
            )
        },
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(characters.itemCount) { index ->
                val character = characters[index]!!
                Text(
                    text = "Character ${character.name}",
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { onItemClick(character.id.toInt()) }
                        .padding(8.dp),
                    style = MaterialTheme.typography.bodyLarge
                )

            }
        }
    }
}


@Preview
@Composable
fun CharactersScreenPreview() {
    CharactersScreen(
        charactersState = CharactersState(),
        charactersEvent = {},
        onItemClick = {})
}


