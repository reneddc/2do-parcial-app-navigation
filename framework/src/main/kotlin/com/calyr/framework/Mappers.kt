package com.calyr.framework

import com.calyr.domain.Movie
import com.calyr.framework.local.MovieEntity
import com.calyr.framework.network.MovieRemote

fun MovieRemote.toMovie(id: Int) : Movie {
    return Movie(
        id = id,
        title = title,
        description = description,
        posterPath = "https://image.tmdb.org/t/p/w500$posterPath"
    )
}

fun MovieEntity.toMovie() : Movie {
    return Movie(
        id = id,
        title = title,
        description = description,
        posterPath = posterPath
    )
}

fun Movie.toMovieEntity(): MovieEntity {
    return MovieEntity(
        id, title, description, posterPath
    )
}

