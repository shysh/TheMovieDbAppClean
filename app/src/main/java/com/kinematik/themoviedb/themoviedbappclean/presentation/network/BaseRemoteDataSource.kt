package com.kinematik.themoviedb.themoviedbappclean.presentation.network


import com.kinematik.themoviedb.themoviedbappclean.data.common.Result
import com.kinematik.themoviedb.themoviedbappclean.data.datasource.RemoteDataSource
import retrofit2.Response

/**
 * Abstract Base Data source class with error handling
 */
abstract class BaseRemoteDataSource : RemoteDataSource {

    protected suspend fun <T> getResult(call: suspend () -> Response<T>): Result<T> {
        try {
            val response = call()
            if (response.isSuccessful) {
                val body = response.body()
                if (body != null) return Result.success(body)
            }
            //todo parse error
            return error(Throwable(" ${response.code()} ${response.message()}"))
        } catch (e: Exception) {
            return error(e.message ?: e.toString())
        }
    }

    private fun <T> error(throwable: Throwable): Result<T> {

        return Result.error(throwable)
    }

}

