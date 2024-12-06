package com.example.filmbazzar.data.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NetworkUpcomingResponse(
    @SerialName("results") val networkUpcomingMovies: List<NetworkUpcomingMovie>,
    val page: Int,
    @SerialName("total_pages") val totalPages: Int,
    @SerialName("total_results") val totalResults: Int,
)