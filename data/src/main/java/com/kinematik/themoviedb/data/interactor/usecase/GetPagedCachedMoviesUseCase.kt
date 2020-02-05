package com.kinematik.themoviedb.data.interactor.usecase

import com.kinematik.themoviedb.data.datasource.LocalDataBaseDataSource
import com.kinematik.themoviedb.domain.entity.Movie

class GetPagedCachedMoviesUseCase constructor(private val localDataBaseDataSource: LocalDataBaseDataSource) {

    suspend operator fun invoke(): List<Movie> =
        localDataBaseDataSource.getCachedMovies()
}