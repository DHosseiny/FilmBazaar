package com.example.filmbazzar.domain.repository

import androidx.paging.PagingData
import com.example.filmbazzar.data.database.model.UpcomingMovieEntity
import kotlinx.coroutines.flow.Flow

interface UpcomingMoviesRepository {
    fun getUpcomingMovies(): Flow<PagingData<UpcomingMovieEntity>>
}