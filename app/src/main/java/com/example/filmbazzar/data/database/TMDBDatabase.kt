package com.example.filmbazzar.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.filmbazzar.data.database.model.RemoteKeyEntity
import com.example.filmbazzar.data.database.model.UpcomingMovieEntity

@Database(
    entities = [UpcomingMovieEntity::class, RemoteKeyEntity::class],
    version = 1,
    exportSchema = false
)
abstract class TMDBDatabase : RoomDatabase() {
    abstract fun getUpcomingMoviesDao(): UpcomingMoviesDao
    abstract fun getRemoteKeysDao(): RemoteKeysDao
}