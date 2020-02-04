package com.kinematik.themoviedb.domain.entity

data class Movies(
    val page: Int = 0,
    val total_results: Int = 0,
    val total_pages: Int = 0,
    val results: List<Movie> = listOf()
)