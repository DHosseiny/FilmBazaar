package com.example.filmbazzar.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.filmbazzar.data.NETWORK_PAGE_SIZE
import com.example.filmbazzar.data.database.TMDBDatabase
import com.example.filmbazzar.data.database.model.UpcomingMovieEntity
import com.example.filmbazzar.data.network.TMDBService
import com.example.filmbazzar.data.paging.UpcomingPagingSource
import com.example.filmbazzar.domain.repository.UpcomingMoviesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UpcomingMoviesRepositoryImp @Inject constructor(
    private val tmdbService: TMDBService,
    private val tmdbDatabase: TMDBDatabase,

    ) : UpcomingMoviesRepository {
    override fun getUpcomingMovies(): Flow<PagingData<UpcomingMovieEntity>> {
        return Pager(
            config = PagingConfig(
                enablePlaceholders = false,
                pageSize = NETWORK_PAGE_SIZE,
                initialLoadSize = NETWORK_PAGE_SIZE,
            ),
            pagingSourceFactory = {
                UpcomingPagingSource(tmdbService,tmdbDatabase)
            }
        ).flow
    }
}