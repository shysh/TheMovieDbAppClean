package com.kinematik.themoviedb.themoviedbappclean.presentation.common

import androidx.paging.PageKeyedDataSource
import com.kinematik.themoviedb.themoviedbappclean.data.datasource.LocalDataBaseDataSource
import com.kinematik.themoviedb.themoviedbappclean.data.datasource.RemoteDataSource
import com.kinematik.themoviedb.themoviedbappclean.presentation.common.model.Movie
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import javax.inject.Inject

class MoviesRemotePagedDataSource @Inject constructor(
    private val dateFrom: String,
    private val dateTo: String,
    private val remoteDataSource: RemoteDataSource,
    private val localDataBaseDataSource: LocalDataBaseDataSource,
    private val coroutineScope: CoroutineScope):
    PageKeyedDataSource<Int, Movie>(){

    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, Movie>) {
        fetchData(1, params.requestedLoadSize) {
            callback.onResult(it, null, 2)
        }
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Movie>) {
        val page = params.key
        fetchData(page, params.requestedLoadSize) {
            callback.onResult(it, page + 1)
        }
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Movie>) {
        val page = params.key
        fetchData(page, params.requestedLoadSize) {
            callback.onResult(it, page - 1)
        }
    }

    private fun fetchData(page: Int, pageSize: Int, callback: (List<Movie>) -> Unit) {
        coroutineScope.launch(getJobErrorHandler()) {
            val response = remoteDataSource.getMovies(dateFrom, dateTo, page, pageSize)
            if (response.status == Result.Status.SUCCESS) {
                val results = response.data!!.results
                dao.insertAll(results)
                callback(results)
            } else if (response.status == Result.Status.ERROR) {
                postError(response.message!!)
            }
        }
    }

    private fun getJobErrorHandler() = CoroutineExceptionHandler { _, e ->
        postError(e.message ?: e.toString())
    }

    private fun postError(message: String) {
        // TODO network error handling
        //networkState.postValue(NetworkState.FAILED)
    }
}