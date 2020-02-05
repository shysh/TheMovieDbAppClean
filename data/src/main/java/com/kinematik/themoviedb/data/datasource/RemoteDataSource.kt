package com.kinematik.themoviedb.data.datasource

import com.kinematik.themoviedb.domain.entity.Movie
import com.kinematik.themoviedb.domain.entity.Page
import com.kinematik.themoviedb.domain.common.DataResult

interface RemoteDataSource {
    suspend fun getMovies(
        dateFrom: String,
        dateTo: String,
        page: Int = 0,
        pageSize: Int = 20
    ): DataResult<Page<Movie>>

    suspend fun getMovie(id: Int): DataResult<Movie>
}