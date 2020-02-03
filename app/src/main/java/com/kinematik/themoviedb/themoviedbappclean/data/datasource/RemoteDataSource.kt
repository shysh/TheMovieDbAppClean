package com.kinematik.themoviedb.themoviedbappclean.data.datasource

import com.kinematik.themoviedb.app.core.domain.Movie
import com.kinematik.themoviedb.app.core.domain.Movies

import com.kinematik.themoviedb.themoviedbappclean.data.common.Result

interface RemoteDataSource {
    suspend fun getMovies(dateFrom: String, dateTo: String, page: Int = 0, pageSize: Int = 20): Movies
    suspend fun getMovie(id: Int): Movie
}