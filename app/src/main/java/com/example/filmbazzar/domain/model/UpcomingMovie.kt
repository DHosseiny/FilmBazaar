package com.example.filmbazzar.domain.model

data class UpcomingMovie(
    val sortId: Int,
    val movieId: Int,
    val title: String?,
    val posterPath: String?,
)