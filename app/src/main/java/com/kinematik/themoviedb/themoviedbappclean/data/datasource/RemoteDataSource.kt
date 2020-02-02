package com.kinematik.themoviedb.themoviedbappclean.data.datasource

import com.kinematik.themoviedb.app.core.domain.Movie
import com.kinematik.themoviedb.app.core.domain.Movies

interface RemoteDataSource {
    suspend fun getMovies(dateFrom: String, dateTo: String, page: Int = 0): Movies
    suspend fun getMovie(id: Int): Movie
}