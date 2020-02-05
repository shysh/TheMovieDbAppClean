package com.kinematik.themoviedb.themoviedbappclean.farmework.db.dao

import androidx.room.*
import com.kinematik.themoviedb.themoviedbappclean.farmework.db.entity.MovieDBEntity

@Dao
interface MoviesDao {

    @Query("SELECT * FROM movies")
    suspend fun getMovies(): List<MovieDBEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(movies: List<MovieDBEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(movie: MovieDBEntity)

}