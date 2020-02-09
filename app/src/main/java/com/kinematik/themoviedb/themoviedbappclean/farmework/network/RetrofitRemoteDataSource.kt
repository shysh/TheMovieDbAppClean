package com.kinematik.themoviedb.themoviedbappclean.farmework.network

import com.kinematik.themoviedb.app.core.domain.MoviesResponse
import com.kinematik.themoviedb.data.datasource.RemoteDataSource
import com.kinematik.themoviedb.domain.common.DataResult
import com.kinematik.themoviedb.domain.entity.Movie
import com.kinematik.themoviedb.domain.entity.Page
import com.kinematik.themoviedb.themoviedbappclean.farmework.network.mapper.MoviesResponseMapper
import retrofit2.Response
import javax.inject.Inject

class RetrofitRemoteDataSource @Inject constructor(private val moviesApiService: MoviesApiService) :
    RemoteDataSource {

    val moviesResponseMapper = MoviesResponseMapper()

    override suspend fun getMovies(
        dateFrom: String,
        dateTo: String,
        page: Int,
        pageSize: Int
    ): DataResult<Page<Movie>> {
        var result = DataResult<Page<Movie>>()

        val apiResult = getResult<MoviesResponse> {
            moviesApiService.discoverMovies(dateFrom, dateTo, page)
        }

        if (apiResult.status == DataResult.Status.SUCCESS) {
            apiResult.data?.let {
                result = DataResult.success(moviesResponseMapper.mapToEntity(it))
            }

        } else {
            result = DataResult.error(apiResult.error)
        }

        return result
    }

    override suspend fun getMovie(id: Int): DataResult<Movie> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


    private suspend fun <T> getResult(call: suspend () -> Response<T>): DataResult<T> {
        try {
            val response = call()
            if (response.isSuccessful) {
                val body = response.body()
                if (body != null) return DataResult.success(body)
            }

            return error(Throwable(" ${response.code()} ${response.message()}"))
        } catch (e: Exception) {
            return error(e)
        }
    }

    private fun <T> error(throwable: Throwable): DataResult<T> {
        return DataResult.error(throwable)
    }
}