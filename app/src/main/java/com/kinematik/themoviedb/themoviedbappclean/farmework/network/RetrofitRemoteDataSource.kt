package com.kinematik.themoviedb.themoviedbappclean.farmework.network

import com.kinematik.themoviedb.data.datasource.RemoteDataSource
import com.kinematik.themoviedb.domain.common.Result
import com.kinematik.themoviedb.domain.entity.Movie
import com.kinematik.themoviedb.domain.entity.Movies
import retrofit2.Response
import javax.inject.Inject

class RetrofitRemoteDataSource @Inject constructor(private val moviesApiService: MoviesApiService) :
    RemoteDataSource {
    override suspend fun getMovies(
        dateFrom: String,
        dateTo: String,
        page: Int,
        pageSize: Int
    ): Result<Movies> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override suspend fun getMovie(id: Int): Result<Movie> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    /*override suspend fun getMovies(dateFrom: String, dateTo: String, page: Int, pageSize: Int): Result<Movies> {
        getResult({moviesApiService.discoverMovies(dateFrom, dateTo, page)})
    }

    override suspend fun getMovie(id: Int): Result<Movie>  = getResult {
        moviesApiService.getMovie(id)
    }*/


    private suspend fun <T, E> getResult(call: suspend () -> Response<T>): Result<T> {
        try {
            val response = call()
            if (response.isSuccessful) {
                val body = response.body()
                if (body != null) return Result.success(body)
            }

            return error(Throwable(" ${response.code()} ${response.message()}"))
        } catch (e: Exception) {
            return error(e)
        }
    }

    private fun <T> error(throwable: Throwable): Result<T> {
        return Result.error(throwable)
    }
}