package com.kinematik.themoviedb.app.core.domain

import com.google.gson.annotations.SerializedName

data class MoviesResponse(
    @SerializedName("page") val page: Int = 0,
    @SerializedName("total_results") val total_results: Int = 0,
    @SerializedName("total_pages") val total_pages: Int = 0,
    @SerializedName("results") val results: List<MovieResponse> = listOf()
)