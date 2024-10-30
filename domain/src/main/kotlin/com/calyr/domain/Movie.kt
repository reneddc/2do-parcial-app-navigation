package com.calyr.domain

import kotlinx.serialization.Serializable

@Serializable
data class Movie(val id: String, val title: String, val description: String, val posterPath: String)