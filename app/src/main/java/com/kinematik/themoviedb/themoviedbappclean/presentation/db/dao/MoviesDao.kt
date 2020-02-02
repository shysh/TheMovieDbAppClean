package com.kinematik.themoviedb.themoviedbappclean.presentation.db.dao

import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.kinematik.themoviedb.themoviedbappclean.presentation.db.entity.MovieDBEntity

@Dao
interface MoviesDao {

    @Query("SELECT * FROM movies")
    fun getPagedMovies(): DataSource.Factory<Int, MovieDBEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(movies: List<MovieDBEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(movie: MovieDBEntity)
}