package com.kinematik.themoviedb.themoviedbappclean.data.datasource

import com.kinematik.themoviedb.app.core.domain.MovieResponse

interface LocalDataBaseDataSource {

    suspend fun getMovies(dateFrom: String, dateTo: String): List<MovieResponse>

    suspend fun getMoviesFromFavourites(): List<MovieResponse>

    suspend fun saveMovieToFavourites(movie: MovieResponse)

    suspend fun removeMovieFromFavourites(movie: MovieResponse)
}