package com.kinematik.themoviedb.data.interactor.usecase

import com.kinematik.themoviedb.data.datasource.LocalDataBaseDataSource
import com.kinematik.themoviedb.domain.entity.Movie

class SaveMoviesToLocalUseCase(private val localDataBaseDataSource: LocalDataBaseDataSource) {

    suspend operator fun invoke(movie: Movie) = localDataBaseDataSource.saveMovieToFavourites(movie)

}