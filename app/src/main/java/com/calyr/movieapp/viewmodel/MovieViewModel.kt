package com.calyr.movieapp.viewmodel

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.calyr.data.MovieRepository
import com.calyr.domain.Movie
import com.calyr.movieapp.util.NetworkUtils  // Importa NetworkUtils
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(
    private val movieRepository: MovieRepository
) : ViewModel() {

    sealed class MovieState {
        object Loading : MovieState()
        class Error(val errorMessage: String? = null) : MovieState()
        class Successful(val list: List<Movie> = emptyList()) : MovieState()
    }

    private val _state = MutableStateFlow<MovieState>(MovieState.Loading)
    val state: StateFlow<MovieState> = _state

    // Modificación: Añadimos un parámetro `context` para verificar la conexión
    fun fetchData(context: Context) {
        viewModelScope.launch(Dispatchers.IO) {
            // Verifica la conexión a Internet antes de proceder
            if (!NetworkUtils.isInternetAvailable(context)) {
                withContext(Dispatchers.Main) {
                    _state.value = MovieState.Error("Sin conexión a Internet")
                }
                return@launch
            }

            try {
                val movies = movieRepository.obtainMovies()
                withContext(Dispatchers.Main) {
                    _state.value = MovieState.Successful(list = movies)
                }
            } catch (e: Exception) {
                Log.e("MOVIE", "Error fetching movies", e)
                withContext(Dispatchers.Main) {
                    _state.value = MovieState.Error(errorMessage = e.message)
                }
            }
        }
    }
}
