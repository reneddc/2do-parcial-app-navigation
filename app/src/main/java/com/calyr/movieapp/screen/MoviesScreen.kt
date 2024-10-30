package com.calyr.movieapp.screen

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun MoviesScreen( onClick : () -> Unit) {
    Scaffold(
        content = {
            paddingValues -> MoviesScreenContent(
            modifier = Modifier.padding(paddingValues),
                onClick = onClick)
        }
    )
}

@Composable
fun MoviesScreenContent(modifier: Modifier, onClick: () -> Unit) {
        Button(
            onClick = {
                onClick()
            }
        ) {
            Text(text = "Ir al detalle")
        }
}