package com.kinematik.themoviedb.themoviedbappclean.presentation.network

import com.kinematik.themoviedb.app.core.domain.Movie
import com.kinematik.themoviedb.app.core.domain.Movies
import com.kinematik.themoviedb.themoviedbappclean.data.datasource.RemoteDataSource
import javax.inject.Inject

class RetrofitRemoteDataSource @Inject constructor(private val moviesApiService: MoviesApiService): BaseRemoteDataSource() {

    override suspend fun getMovies(dateFrom: String, dateTo: String, page: Int, pageSize: Int): Movies {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override suspend fun getMovie(id: Int): Movie {

    }
}