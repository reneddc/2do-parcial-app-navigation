package com.calyr.movieapp.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.calyr.data.MovieRepository
import com.calyr.domain.Movie
import com.calyr.framework.local.AppRoomDatabase
import com.calyr.framework.network.RemoteDataSource
import com.calyr.framework.network.RetrofitBuilder
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import com.calyr.framework.local.LocalDataSource
class MovieViewModel : ViewModel() {

    sealed class MovieState {
        object Loading : MovieState()
        class Error(val message: String) : MovieState()
        class Successful(val list: List<Movie>) : MovieState()
    }

    val state : LiveData<MovieState>
        get() = _state
    private val _state = MutableLiveData<MovieState>()

    fun fetchData(context: Context) {

        CoroutineScope(Dispatchers.IO).launch {
            val movies = MovieRepository(
                RemoteDataSource(
                    RetrofitBuilder
                ),
                LocalDataSource(
                    context
                )
            ).obtainMovies()
            withContext(Dispatchers.Main) {
                _state.value = MovieState.Successful(movies)
            }
        }
    }

}