package com.example.filmbazzar.ui.upcoming

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.filmbazzar.domain.model.UpcomingMovie
import com.example.filmbazzar.domain.usecase.GetUpcomingMoviesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class UpcomingMoviesViewModel @Inject constructor(
    getUpcomingMoviesUseCase: GetUpcomingMoviesUseCase
) : ViewModel() {

    val upcomingMovies: Flow<PagingData<UpcomingMovie>> = getUpcomingMoviesUseCase().cachedIn(viewModelScope)

}