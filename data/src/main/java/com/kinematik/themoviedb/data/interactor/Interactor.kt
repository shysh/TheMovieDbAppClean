package com.kinematik.themoviedb.data.interactor

import com.kinematik.themoviedb.data.datasource.LocalDataBaseDataSource
import com.kinematik.themoviedb.data.datasource.RemoteDataSource
import com.kinematik.themoviedb.domain.entity.Movie

abstract class MoviesInteractor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataBaseDataSource: LocalDataBaseDataSource

) {
    suspend fun getMovies(
        dateFrom: String,
        dateTo: String,
        page: Int = 0,
        pageSize: Int = 20
    ) = remoteDataSource.getMovies(
        dateFrom, dateTo, page, pageSize
    )



    suspend  fun getCachedMovies(): List<Movie> =
        localDataBaseDataSource.getCachedMovies()

    suspend fun saveMovieToFavourites(movie: Movie) = localDataBaseDataSource.saveMovieToFavourites(movie)
}