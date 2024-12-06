package com.example.filmbazzar.data.database

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.filmbazzar.data.database.model.UpcomingMovieEntity

@Dao
interface UpcomingMoviesDao {

    @Insert
    suspend fun insertUpcomingMovies(movies: List<UpcomingMovieEntity>)

    @Query("SELECT * FROM upcoming_movies LIMIT :limit OFFSET :offset")
    fun getUpcomingMovies(limit: Int, offset: Int): List<UpcomingMovieEntity>

    @Query("Delete From upcoming_movies")
    suspend fun clearAllUpcomingMovies()
}