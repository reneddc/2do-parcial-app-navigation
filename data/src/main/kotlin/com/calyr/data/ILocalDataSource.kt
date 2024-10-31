package com.calyr.data

import com.calyr.domain.Movie

interface ILocalDataSource {
    fun getList(): List<Movie>
}