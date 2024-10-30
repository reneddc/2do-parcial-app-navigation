package com.calyr.movieapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.calyr.data.MovieRepository
import com.calyr.domain.Movie
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
        _state.value = MovieState.Successful( MovieRepository().obtainMovies() )
    }

}