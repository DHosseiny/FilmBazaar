package com.example.filmbazzar.data.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.filmbazzar.domain.model.UpcomingMovie

@Entity(tableName = "upcoming_movies")
data class UpcomingMovieEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "sort_id")
    val sortId: Int = 0,
    @ColumnInfo(name = "movie_id")
    val movieId: Int,
    val title: String?,
    @ColumnInfo("poster_path") val posterPath: String?,
    @ColumnInfo("network_page")
    val networkPage: Int,
)

fun UpcomingMovieEntity.asUiModel() = UpcomingMovie(
    sortId = sortId,
    movieId = movieId,
    title = title,
    posterPath = posterPath
)