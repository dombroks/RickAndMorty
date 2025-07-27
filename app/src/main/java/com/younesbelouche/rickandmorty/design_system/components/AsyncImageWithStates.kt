package com.younesbelouche.rickandmorty.design_system.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest

@Composable
fun AsyncImageWithStates(
    imageUrl: String,
    modifier: Modifier = Modifier,
    placeholderIcon: ImageVector = Icons.Default.Person,
    errorIcon: ImageVector = Icons.Default.Warning,
    placeholderPadding: Dp = 16.dp,
    errorIconSize: Dp = 64.dp,
    loadingIndicatorSize: Dp = 32.dp,
    showLoadingText: Boolean = false,
    showErrorText: Boolean = false,
    errorText: String = "Image not available",
    contentDescription: String? = null,
    overlay: @Composable (() -> Unit)? = null
) {
    if (LocalInspectionMode.current) {
        Surface(
            modifier = modifier,
            color = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.3f)
        ) {
            Icon(
                imageVector = placeholderIcon,
                contentDescription = "Placeholder",
                modifier = Modifier
                    .fillMaxSize()
                    .padding(placeholderPadding),
                tint = MaterialTheme.colorScheme.primary
            )
        }
    } else {
        val painter = rememberAsyncImagePainter(
            model = ImageRequest.Builder(LocalContext.current)
                .data(imageUrl)
                .size(coil.size.Size.ORIGINAL)
                .crossfade(true)
                .build()
        )

        when (painter.state) {
            is AsyncImagePainter.State.Loading -> {
                Surface(
                    modifier = modifier,
                    color = MaterialTheme.colorScheme.surfaceVariant
                ) {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            CircularProgressIndicator(
                                modifier = Modifier.size(loadingIndicatorSize),
                                strokeWidth = 3.dp,
                                color = MaterialTheme.colorScheme.primary
                            )
                            if (showLoadingText) {
                                Text(
                                    text = "Loading image...",
                                    style = MaterialTheme.typography.bodyMedium,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant
                                )
                            }
                        }
                    }
                }
            }

            is AsyncImagePainter.State.Error -> {
                Surface(
                    modifier = modifier,
                    color = MaterialTheme.colorScheme.errorContainer.copy(alpha = 0.3f)
                ) {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Icon(
                            imageVector = errorIcon,
                            contentDescription = "Error loading image",
                            modifier = Modifier.size(errorIconSize),
                            tint = MaterialTheme.colorScheme.error
                        )
                        if (showErrorText) {
                            Text(
                                text = errorText,
                                style = MaterialTheme.typography.bodyMedium,
                                color = MaterialTheme.colorScheme.onErrorContainer,
                                modifier = Modifier.padding(top = 8.dp)
                            )
                        }
                    }
                }
            }

            else -> {
                Box(modifier = modifier) {
                    Image(
                        painter = painter,
                        contentDescription = contentDescription,
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Crop
                    )
                    overlay?.invoke()
                }
            }
        }
    }
}
