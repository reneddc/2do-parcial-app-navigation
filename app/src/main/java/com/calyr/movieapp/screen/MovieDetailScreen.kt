package com.calyr.movieapp.screen

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Observer
import androidx.lifecycle.compose.LocalLifecycleOwner
import coil.compose.AsyncImage
import com.calyr.domain.Movie
import com.calyr.movieapp.viewmodel.MovieDetailViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieDetailScreen(onBackPressed: () -> Unit, movieId: String) {
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
            movieId = movieId)
        }
    )
}

@Composable
fun MovieDetailScreenContent(modifier: Modifier, movieId: String) {
    val viewModel = MovieDetailViewModel()
    var movieUI by remember { mutableStateOf(Movie(1, "", "", "")) }

    viewModel.findMovie(LocalContext.current, movieId)

    fun updateUI(movieDetailState: MovieDetailViewModel.MovieDetailState) {
        when (movieDetailState) {
            is MovieDetailViewModel.MovieDetailState.Successful -> {
                movieUI = movieDetailState.movie
            }
            // Manejo de otros estados: Error y Loading (similar a lo ya implementado)
            else -> {}
        }
    }

    viewModel.state.observe(LocalLifecycleOwner.current, Observer(::updateUI))

    Column(
        modifier = modifier
    ) {
        // Mostrar la imagen del poster
        AsyncImage(
            model = movieUI.posterPath,
            contentDescription = movieUI.title,
            modifier = Modifier
                .height(300.dp)
                .fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = movieUI.title)
        Text(text = movieUI.description)
    }
}