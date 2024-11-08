package com.calyr.movieapp.viewmodel

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.calyr.data.MovieRepository
import com.calyr.domain.Movie
import com.calyr.movieapp.util.NetworkUtils
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

    fun fetchData(context: Context) {
        viewModelScope.launch(Dispatchers.IO) {
            if (!NetworkUtils.isInternetAvailable(context)) {
                // Sin conexión, obtener datos solo de la base de datos local
                val localMovies = movieRepository.getLocalMovies()
                withContext(Dispatchers.Main) {
                    _state.value = if (localMovies.isNotEmpty()) {
                        MovieState.Successful(localMovies)
                    } else {
                        MovieState.Error("No hay datos disponibles sin conexión")
                    }
                }
                return@launch
            }

            // Con conexión a Internet, obtener datos remotos y actualizar base de datos
            try {
                val movies = movieRepository.obtainMovies()
                withContext(Dispatchers.Main) {
                    _state.value = MovieState.Successful(list = movies)
                }
            } catch (e: Exception) {
                Log.e("MOVIE", "Error al obtener las películas", e)
                withContext(Dispatchers.Main) {
                    _state.value = MovieState.Error(errorMessage = e.message)
                }
            }
        }
    }
}
