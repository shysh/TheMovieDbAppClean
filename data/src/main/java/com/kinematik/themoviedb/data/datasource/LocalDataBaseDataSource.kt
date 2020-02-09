package com.kinematik.themoviedb.data.datasource

import com.kinematik.themoviedb.domain.entity.Movie
import kotlinx.coroutines.channels.Channel


interface LocalDataBaseDataSource {

    suspend fun getCachedMoviesChannel():Channel<List<Movie>>

    suspend fun getCachedMovies(): List<Movie>

    suspend fun getMoviesFromFavourites(): List<Movie>

    suspend fun saveMovieToFavourites(movie: Movie)

    suspend fun removeMovieFromFavourites(movie: Movie)

    suspend fun clearAll()

    suspend fun insertAll(items: List<Movie>)
}