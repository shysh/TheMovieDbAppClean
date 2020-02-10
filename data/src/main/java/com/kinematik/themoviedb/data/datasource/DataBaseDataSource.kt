package com.kinematik.themoviedb.data.datasource

import com.kinematik.themoviedb.domain.entity.Movie


interface DataBaseDataSource {

    suspend fun getCachedMovies(): List<Movie>

    suspend fun getMoviesFromFavourites(): List<Movie>

    suspend fun saveMovieToFavourites(movie: Movie)

    suspend fun removeMovieFromFavourites(movie: Movie)
}