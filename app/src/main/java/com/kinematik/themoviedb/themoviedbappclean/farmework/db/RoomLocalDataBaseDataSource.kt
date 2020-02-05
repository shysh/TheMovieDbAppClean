package com.kinematik.themoviedb.themoviedbappclean.farmework.db

import com.kinematik.themoviedb.data.datasource.LocalDataBaseDataSource
import com.kinematik.themoviedb.domain.entity.Movie
import com.kinematik.themoviedb.themoviedbappclean.farmework.db.mapper.MovieDBEntityMapper
import javax.inject.Inject

class RoomLocalDataBaseDataSource @Inject constructor(val roomDataBase: MoviesRoomDataBase) : LocalDataBaseDataSource {

    val movieDBEntityMapper = MovieDBEntityMapper()

    override suspend fun getCachedMovies(): List<Movie> {
        return roomDataBase.moviesDao().getMovies().map {
            movieDBEntityMapper.mapToEntity(it)
        }
    }

    override suspend fun getMoviesFromFavourites(): List<Movie> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override suspend fun saveMovieToFavourites(movie: Movie) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override suspend fun removeMovieFromFavourites(movie: Movie) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}