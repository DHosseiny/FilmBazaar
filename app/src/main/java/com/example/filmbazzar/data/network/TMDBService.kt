package com.example.filmbazzar.data.network

import com.example.filmbazzar.data.network.model.NetworkUpcomingMovie
import com.example.filmbazzar.data.network.model.NetworkUpcomingResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface TMDBService {
    @GET("upcoming")
    suspend fun getUpcomingMovies(
        @Query("page") page: Int,
    ): NetworkUpcomingResponse
}