package com.example.filmbazzar.domain.di

import com.example.filmbazzar.data.repository.UpcomingMoviesRepositoryImp
import com.example.filmbazzar.domain.repository.UpcomingMoviesRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@InstallIn(SingletonComponent::class)
@Module
interface DataModule {
    @Binds
    fun bindsUpcomingMoviesRepository(
        upcomingMoviesRepositoryImp: UpcomingMoviesRepositoryImp,
    ): UpcomingMoviesRepository
}