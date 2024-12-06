package com.example.filmbazzar.data.paging

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import androidx.room.withTransaction
import com.example.filmbazzar.data.MOVIE_LOAD_LIMIT
import com.example.filmbazzar.data.NETWORK_PAGE_SIZE
import com.example.filmbazzar.data.REMOTE_KEY_ID
import com.example.filmbazzar.data.database.TMDBDatabase
import com.example.filmbazzar.data.database.model.RemoteKeyEntity
import com.example.filmbazzar.data.database.model.UpcomingMovieEntity
import com.example.filmbazzar.data.network.TMDBService
import com.example.filmbazzar.data.network.model.asEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

private const val STARTING_PAGE_INDEX = 1

class UpcomingPagingSource(
    private val tmdbService: TMDBService,
    private val tmdbDatabase: TMDBDatabase
) : PagingSource<Int, UpcomingMovieEntity>() {

    private val remoteKeysDao = tmdbDatabase.getRemoteKeysDao()
    private val upcomingMoviesDao = tmdbDatabase.getUpcomingMoviesDao()
    override fun getRefreshKey(state: PagingState<Int, UpcomingMovieEntity>): Int? {
        return null
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, UpcomingMovieEntity> {
        val pagingPage = params.key ?: STARTING_PAGE_INDEX
        return withContext(Dispatchers.IO) {
            return@withContext try {
                val endOfPaginationReached =
                    remoteKeysDao.getLastRemoteKey(REMOTE_KEY_ID)?.totalPage == pagingPage
                var upcomingMoviesEntity =
                    upcomingMoviesDao.getUpcomingMovies(
                        NETWORK_PAGE_SIZE,
                        (pagingPage - 1) * NETWORK_PAGE_SIZE
                    )
                if (upcomingMoviesEntity.isEmpty()) {
                    val response = tmdbService.getUpcomingMovies(pagingPage)
                    val upcomingMoviesNetwork = response.networkUpcomingMovies
                    tmdbDatabase.withTransaction {
                        upcomingMoviesDao.insertUpcomingMovies(upcomingMoviesNetwork.map {
                            it.asEntity(
                                response.page
                            )
                        })
                    }
                    upcomingMoviesEntity = upcomingMoviesDao.getUpcomingMovies(
                        MOVIE_LOAD_LIMIT,
                        (pagingPage - 1) * MOVIE_LOAD_LIMIT
                    )
                    remoteKeysDao.insertKey(
                        RemoteKeyEntity(
                            REMOTE_KEY_ID,
                            response.page,
                            response.totalPages
                        )
                    )
                }
                LoadResult.Page(
                    data = upcomingMoviesEntity,
                    prevKey = if (pagingPage == STARTING_PAGE_INDEX) null else pagingPage - 1,
                    nextKey = if (endOfPaginationReached) null else pagingPage + 1
                )

            } catch (exception: Exception) {
                LoadResult.Error(exception)
            }
        }
    }
}