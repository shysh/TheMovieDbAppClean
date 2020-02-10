package com.kinematik.themoviedb.data.usecase

import com.kinematik.themoviedb.data.datasource.DataBaseDataSource
import com.kinematik.themoviedb.domain.entity.Movie

class GetCachedMoviesUseCase constructor(private val localDataBaseDataSource: DataBaseDataSource) {

    suspend operator fun invoke(): List<Movie> =
        localDataBaseDataSource.getCachedMovies()
}