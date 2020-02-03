package com.kinematik.themoviedb.data.datasource

import com.kinematik.themoviedb.domain.entity.Movie
import com.kinematik.themoviedb.domain.entity.Movies
import com.kinematik.themoviedb.domain.common.Result

interface RemoteDataSource {
    suspend fun getMovies(
        dateFrom: String,
        dateTo: String,
        page: Int = 0,
        pageSize: Int = 20
    ): Result<Movies>

    suspend fun getMovie(id: Int): Result<Movie>
}