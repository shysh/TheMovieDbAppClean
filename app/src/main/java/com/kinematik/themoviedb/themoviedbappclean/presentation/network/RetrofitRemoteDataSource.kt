package com.kinematik.themoviedb.themoviedbappclean.presentation.network

import com.kinematik.themoviedb.app.core.domain.Movie
import com.kinematik.themoviedb.app.core.domain.Movies
import com.kinematik.themoviedb.themoviedbappclean.data.datasource.RemoteDataSource
import com.kinematik.themoviedb.themoviedbappclean.domain.common.Result
import com.kinematik.themoviedb.themoviedbappclean.presentation.network.mapper.MovieResponseMapper
import retrofit2.Response
import javax.inject.Inject

class RetrofitRemoteDataSource @Inject constructor(private val moviesApiService: MoviesApiService, val mapper: MovieResponseMapper): RemoteDataSource {

    override suspend fun getMovies(dateFrom: String, dateTo: String, page: Int, pageSize: Int): Result<Movies> {

    }

    override suspend fun getMovie(id: Int): Result<Movie>  = getResult{
        moviesApiService.getMovie(id)
    }

    private suspend fun <T> getResult(call: suspend () -> Response<T>): Result<T> {
        try {
            val response = call()
            if (response.isSuccessful) {
                val body = response.body()
                if (body != null) return Result.success(body)
            }
            //todo parse error
            return error(Throwable(" ${response.code()} ${response.message()}"))
        } catch (e: Exception) {
            return error(e)
        }
    }

    private fun <T> error(throwable: Throwable): Result<T> {
        return Result.error(throwable)
    }
}