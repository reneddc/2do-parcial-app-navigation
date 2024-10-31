package com.calyr.movieapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.calyr.data.MovieRepository
import com.calyr.domain.Movie
import com.calyr.framework.network.RemoteDataSource
import com.calyr.framework.network.RetrofitBuilder
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MovieViewModel : ViewModel() {

    sealed class MovieState {
        object Loading : MovieState()
        class Error(val message: String) : MovieState()
        class Successful(val list: List<Movie>) : MovieState()
    }

    val state : LiveData<MovieState>
        get() = _state
    private val _state = MutableLiveData<MovieState>()

    fun fetchData() {

        CoroutineScope(Dispatchers.IO).launch {
            val movies = MovieRepository(
                RemoteDataSource(
                    RetrofitBuilder
                )
            ).obtainMovies()
            withContext(Dispatchers.Main) {
                _state.value = MovieState.Successful(movies)
            }
        }
    }

}