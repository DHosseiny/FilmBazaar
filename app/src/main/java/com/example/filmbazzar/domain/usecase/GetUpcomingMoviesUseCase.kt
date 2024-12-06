package com.example.filmbazzar.domain.usecase

import androidx.paging.PagingData
import androidx.paging.map
import com.example.filmbazzar.data.database.model.asUiModel
import com.example.filmbazzar.domain.model.UpcomingMovie
import com.example.filmbazzar.domain.repository.UpcomingMoviesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetUpcomingMoviesUseCase @Inject constructor(
    private val upcomingMoviesRepository: UpcomingMoviesRepository
) {
    operator fun invoke(): Flow<PagingData<UpcomingMovie>> =
        upcomingMoviesRepository.getUpcomingMovies().map { pagingData ->
            pagingData.map { upcomingMovieEntity ->
                upcomingMovieEntity.asUiModel()
            }
        }
}