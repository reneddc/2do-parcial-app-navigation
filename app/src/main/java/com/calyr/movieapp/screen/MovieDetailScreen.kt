package com.calyr.movieapp.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.calyr.domain.Movie

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieDetailScreen(onBackPressed: () -> Unit, movie: com.calyr.domain.Movie) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Movie Details")
                },
                navigationIcon = {
                    IconButton(
                        onClick = onBackPressed,
                        content = {
                            Icon(
                                imageVector = Icons.Default.ArrowBack,
                                contentDescription = "Back"
                            )
                        }
                    )
                }
            )
        },
        content = {
                paddingValues -> MovieDetailScreenContent( modifier = Modifier.padding(paddingValues),
                    movie = movie)
        }
    )
}

@Composable
fun MovieDetailScreenContent(modifier: Modifier, movie: com.calyr.domain.Movie) {
    Column(
        modifier = modifier
    ) {
        Text(text = movie.title)
        Text(text = movie.description)
    }
}