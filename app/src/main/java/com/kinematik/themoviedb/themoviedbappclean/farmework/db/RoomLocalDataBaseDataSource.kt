package com.kinematik.themoviedb.themoviedbappclean.farmework.db

import com.kinematik.themoviedb.data.datasource.DataBaseDataSource
import com.kinematik.themoviedb.domain.entity.Movie
import com.kinematik.themoviedb.themoviedbappclean.farmework.db.mapper.MovieDBEntityMapper
import kotlinx.coroutines.channels.Channel
import javax.inject.Inject

class RoomLocalDataBaseDataSource @Inject constructor(val roomDataBase: MoviesRoomDataBase) :
    DataBaseDataSource {

    val movieDBEntityMapper = MovieDBEntityMapper()


    override suspend fun getCachedMovies(): List<Movie> {
        return roomDataBase.moviesDao().getMovies().map {
            movieDBEntityMapper.mapToEntity(it)
        }
    }

    override suspend fun getCachedMoviesChannel(): Channel<List<Movie>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override suspend fun getMoviesFromFavourites(): List<Movie> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override suspend fun saveMovieToFavourites(movie: Movie) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override suspend fun removeMovieFromFavourites(movie: Movie) {

    }

    override suspend fun clearAll() {
        roomDataBase.moviesDao().clearAllMovies()
    }

    override suspend fun insertAll(items: List<Movie>) {
        roomDataBase.moviesDao().insertAll(items.map { movieDBEntityMapper.mapFromEntity(it) })
    }
}