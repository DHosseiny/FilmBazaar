package com.example.filmbazzar.data.network.model

import com.example.filmbazzar.data.BASE_POSTER_PATH
import com.example.filmbazzar.data.database.model.UpcomingMovieEntity
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NetworkUpcomingMovie(
    @SerialName("id") val movieId: Int,
    val title: String?,
    @SerialName("poster_path") val posterPath: String?,
)

fun NetworkUpcomingMovie.asEntity(networkPage: Int) = UpcomingMovieEntity(
    movieId = movieId,
    title = title,
    posterPath = "$BASE_POSTER_PATH$posterPath",
    networkPage = networkPage
)