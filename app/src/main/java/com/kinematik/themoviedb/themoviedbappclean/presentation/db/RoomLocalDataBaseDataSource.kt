package com.kinematik.themoviedb.themoviedbappclean.presentation.db

import com.kinematik.themoviedb.app.core.domain.MovieResponse
import com.kinematik.themoviedb.themoviedbappclean.data.datasource.LocalDataBaseDataSource

class RoomLocalDataBaseDataSource(val roomDataBase: MoviesRoomDataBase): LocalDataBaseDataSource {

    override suspend fun getMovies(dateFrom: String, dateTo: String): List<MovieResponse> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override suspend fun getMoviesFromFavourites(): List<MovieResponse> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override suspend fun saveMovieToFavourites(movie: MovieResponse) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override suspend fun removeMovieFromFavourites(movie: MovieResponse) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}