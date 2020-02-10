package com.kinematik.themoviedb.themoviedbappclean.presentation.common

import androidx.paging.PageKeyedDataSource
import com.kinematik.themoviedb.data.datasource.DataBaseDataSource
import com.kinematik.themoviedb.data.datasource.RemoteDataSource
import com.kinematik.themoviedb.domain.common.DataResult
import com.kinematik.themoviedb.domain.entity.Movie
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import javax.inject.Inject

class MoviesRemotePagedDataSource @Inject constructor(
    private val dateFrom: String,
    private val dateTo: String,
    private val remoteDataSource: RemoteDataSource,
    private val localDataBaseDataSource: DataBaseDataSource,
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
            if (response.status == DataResult.Status.SUCCESS) {

                val items:MutableList<Movie> = mutableListOf<Movie>()

                response.data?.results?.let {
                    if(page == 1){
                        localDataBaseDataSource.clearAll()
                    }

                    items.addAll(it)
                    localDataBaseDataSource.insertAll(it)

                }

                callback(items)
            } else if (response.status == DataResult.Status.ERROR) {
                postError(response.error?.message)
            }
        }
    }

    private fun getJobErrorHandler() = CoroutineExceptionHandler { _, e ->
        postError(e.message ?: e.toString())
    }

    private fun postError(message: String?) {
        // TODO network error handling
        //networkState.postValue(NetworkState.FAILED)
    }
}