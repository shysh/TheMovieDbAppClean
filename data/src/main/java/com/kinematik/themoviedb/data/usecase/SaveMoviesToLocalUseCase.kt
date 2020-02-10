package com.kinematik.themoviedb.data.usecase

import com.kinematik.themoviedb.data.datasource.DataBaseDataSource
import com.kinematik.themoviedb.domain.entity.Movie

class SaveMoviesToLocalUseCase(private val localDataBaseDataSource: DataBaseDataSource) {

    suspend operator fun invoke(movie: Movie) = localDataBaseDataSource.saveMovieToFavourites(movie)

}