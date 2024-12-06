package com.example.filmbazzar.data.di

import android.content.Context
import androidx.room.Room
import com.example.filmbazzar.data.DATABASE_NAME
import com.example.filmbazzar.data.database.RemoteKeysDao
import com.example.filmbazzar.data.database.TMDBDatabase
import com.example.filmbazzar.data.database.UpcomingMoviesDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Singleton
    @Provides
    fun provideTMDBDatabase(@ApplicationContext context: Context): TMDBDatabase {
        return Room.databaseBuilder(context, TMDBDatabase::class.java, DATABASE_NAME).build()
    }

    @Singleton
    @Provides
    fun provideMoviesDao(moviesDatabase: TMDBDatabase): UpcomingMoviesDao =
        moviesDatabase.getUpcomingMoviesDao()

    @Singleton
    @Provides
    fun provideRemoteKeysDao(moviesDatabase: TMDBDatabase): RemoteKeysDao =
        moviesDatabase.getRemoteKeysDao()
}