package com.kinematik.themoviedb.themoviedbappclean.farmework.db.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.kinematik.themoviedb.themoviedbappclean.farmework.db.entity.MovieDBEntity

@Dao
interface MoviesDao {

    @Query("SELECT * FROM movies")
    fun getMoviesLive(): LiveData<List<MovieDBEntity>>

    @Query("SELECT * FROM movies")
    suspend fun getMovies(): List<MovieDBEntity>

    @Query("DELETE  FROM movies")
    suspend fun clearAllMovies()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(movies: List<MovieDBEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(movie: MovieDBEntity)

}