package com.calyr.framework.network

import com.calyr.domain.Movie

fun MovieRemote.toMovie() : Movie {
    return Movie(
        id = "",
        title = title,
        description = description,
        posterPath = posterPath
    )
}

