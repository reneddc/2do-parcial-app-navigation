package com.calyr.movieapp.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Button
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Observer
import androidx.lifecycle.compose.LocalLifecycleOwner
import com.calyr.domain.Movie
import com.calyr.movieapp.viewmodel.MovieViewModel

@Composable
fun MoviesScreen( onClick : (String) -> Unit) {
    Scaffold(
        content = {
            paddingValues -> MoviesScreenContent(
            modifier = Modifier.padding(paddingValues),
                onClick = onClick)
        }
    )
}

@Composable
fun MoviesScreenContent(modifier: Modifier, onClick: (String) -> Unit) {
    var listOfMovies by remember { mutableStateOf(listOf<Movie>()) }

    val movieViewModel = MovieViewModel()
    movieViewModel.fetchData()

    fun updateUI(movieState: MovieViewModel.MovieState) {
        when (movieState) {
            is MovieViewModel.MovieState.Successful -> {
                listOfMovies = movieState.list
            }
            is MovieViewModel.MovieState.Loading -> {

            }
            is MovieViewModel.MovieState.Error -> {

            }
        }
    }
    movieViewModel.state.observe(
        LocalLifecycleOwner.current,
        Observer(::updateUI)
    )


    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        modifier = modifier) {
        items(listOfMovies.size) {
            ElevatedCard(
                elevation = CardDefaults.cardElevation(
                    defaultElevation = 6.dp
                ),
                onClick = {
                    onClick(listOfMovies[it].id)
                }
            ) {
                Text(
                    text = "${listOfMovies[it].title}",
                    modifier = Modifier
                        .padding(16.dp),
                    textAlign = TextAlign.Center
                )
                Text(
                    text = "${listOfMovies[it].description}",
                    modifier = Modifier
                        .padding(10.dp),
                    textAlign = TextAlign.Center,
                )


            }
        }
    }
}