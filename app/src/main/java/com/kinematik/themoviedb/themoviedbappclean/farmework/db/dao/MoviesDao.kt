package com.kinematik.themoviedb.themoviedbappclean.farmework.db.dao

import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.kinematik.themoviedb.themoviedbappclean.farmework.db.entity.MovieDBEntity

@Dao
interface MoviesDao {

    @Query("SELECT * FROM movies")
    suspend fun getPagedMovies(): List<MovieDBEntity>
    //suspend fun getPagedMovies(): DataSource.Factory<Int, MovieDBEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(movies: List<MovieDBEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(movie: MovieDBEntity)
}