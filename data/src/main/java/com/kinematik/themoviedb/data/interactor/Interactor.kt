package com.kinematik.themoviedb.data.interactor

import com.kinematik.themoviedb.data.datasource.CacheDataSource
import com.kinematik.themoviedb.data.datasource.DataBaseDataSource
import com.kinematik.themoviedb.data.datasource.RemoteDataSource
import com.kinematik.themoviedb.domain.entity.Movie

abstract class MoviesInteractor(
    /*private val cacheDataSource: CacheDataSource,*/
    private val remoteDataSource: RemoteDataSource,
    private val localDataBaseDataSource: DataBaseDataSource
) {




    suspend fun getMovies(
        dateFrom: String,
        dateTo: String,
        page: Int = 1,
        pageSize: Int = 20
    ) = remoteDataSource.getMovies(
        dateFrom, dateTo, page, pageSize
    )



    suspend  fun getCachedMovies(): List<Movie> =
        localDataBaseDataSource.getCachedMovies()

    suspend fun saveMovieToFavourites(movie: Movie) = localDataBaseDataSource.saveMovieToFavourites(movie)
}