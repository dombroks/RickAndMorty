package com.younesbelouche.rickandmorty.presentation.characters

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.paging.compose.collectAsLazyPagingItems

import com.younesbelouche.rickandmorty.domain.entities.Character




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

    var searchQuery by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            Column {
                CenterAlignedTopAppBar(
                    title = { Text(text = "Characters Screen") }
                )
                // Search Bar inside screen
                OutlinedTextField(
                    value = searchQuery,
                    onValueChange = { searchQuery = it },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    placeholder = { Text("Search characters...") },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = "Search Icon"
                        )
                    },
                    singleLine = true
                )
            }
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
                val character = characters[index]
                character?.let {
                    CharacterItem(character = it, onClick = { onItemClick(it.id.toInt()) })
                }
            }
        }
    }
}

@Composable
fun CharacterItem(character: Character, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() },
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Row(
            modifier = Modifier.padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
//            Image(
//                painter = rememberAsyncImagePainter(character.image),
//                contentDescription = character.name,
//                modifier = Modifier
//                    .size(64.dp)
//                    .clip(CircleShape)
//            )
            Spacer(modifier = Modifier.width(12.dp))
            Column {
                Text(
                    text = character.name,
                    style = MaterialTheme.typography.titleMedium
                )
                Text(
                    text = character.status,
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.Gray
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


