package com.calyr.movieapp.screen

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.calyr.domain.Movie

@Composable
fun MoviesScreen( onClick : (com.calyr.domain.Movie) -> Unit) {
    Scaffold(
        content = {
            paddingValues -> MoviesScreenContent(
            modifier = Modifier.padding(paddingValues),
                onClick = onClick)
        }
    )
}

@Composable
fun MoviesScreenContent(modifier: Modifier, onClick: (com.calyr.domain.Movie) -> Unit) {

    val movie = com.calyr.domain.Movie(
        title = "Titanes del Pacifico",
        description = "Hace mucho tiempo, legiones de criaturas monstruosas llamados Kaiju surgen del mar, llevando consigo una guerra. Para pelear a los Kaiju, la humanidad desarrolla robots gigantes llamados Jaegers, diseñados para ser piloteados por dos humanos. Sin embargo, ni los Jaegers son suficientes para vencer a los Kaiju, y la humanidad está al borde de la derrota. La última esperanza de la humanidad descansa en un expiloto fracasado, un aprendiz y un viejo y obsoleto Jaeger."
    )
    Button(
            onClick = {
                onClick(movie)
            }
        ) {
            Text(text = "Ir al detalle")
        }
}