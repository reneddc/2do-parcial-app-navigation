package com.calyr.movieapp.screen

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.calyr.domain.Movie
import com.calyr.movieapp.viewmodel.MovieViewModel

@Composable
fun MoviesScreen(onClick: (String) -> Unit, movieViewModel: MovieViewModel) {
    val context = LocalContext.current

    // Llama a `fetchData` automáticamente cuando la pantalla se carga
    LaunchedEffect(Unit) {
        movieViewModel.fetchData(context)
    }

    Scaffold(
        content = { paddingValues ->
            MoviesScreenContent(
                modifier = Modifier.padding(paddingValues),
                onClick = onClick,
                movieViewModel = movieViewModel
            )
        }
    )
}

@Composable
fun MoviesScreenContent(modifier: Modifier, onClick: (String) -> Unit, movieViewModel: MovieViewModel) {
    Log.d("MOVIESCREEN", "MoviesScreenContent")
    var listOfMovies by remember { mutableStateOf(listOf<Movie>()) }
    val context = LocalContext.current

    val movieState by movieViewModel.state.collectAsStateWithLifecycle()

    when (movieState) {
        is MovieViewModel.MovieState.Loading -> {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = modifier.fillMaxSize()
            ) {
                CircularProgressIndicator()
            }
        }
        is MovieViewModel.MovieState.Error -> {
            Toast.makeText(context, "Error: ${(movieState as MovieViewModel.MovieState.Error).errorMessage}", Toast.LENGTH_SHORT).show()
        }
        is MovieViewModel.MovieState.Successful -> {
            listOfMovies = (movieState as MovieViewModel.MovieState.Successful).list
        }
    }

    Column(
        modifier = modifier.fillMaxSize()
    ) {
        Text(text = "Peliculas Populares", modifier = Modifier.padding(16.dp))
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            modifier = modifier
        ) {
            items(listOfMovies.size) { index ->
                ElevatedCard(
                    elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
                    onClick = {
                        onClick(listOfMovies[index].id.toString())
                    },
                    modifier = Modifier.padding(8.dp)
                ) {
                    Text(
                        text = listOfMovies[index].title,
                        modifier = Modifier.padding(16.dp),
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
    }
}
