package com.kinematik.themoviedb.themoviedbappclean.presentation.ui.home.movies

import androidx.paging.PageKeyedDataSource
import com.kinematik.themoviedb.data.datasource.DataBaseDataSource
import com.kinematik.themoviedb.data.datasource.RemoteDataSource
import com.kinematik.themoviedb.domain.common.DataResult
import com.kinematik.themoviedb.domain.entity.Movie
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Data source for lego sets pagination via paging library
 */
class LegoSetPageDataSource @Inject constructor(
    private val dateFrom: String,
    private val dateTo: String,
    private val page: Int = 1,
    private val pageSize: Int = 20,
    private val remoteDataSource: RemoteDataSource,
    private val dataBaseDataSource: DataBaseDataSource,
    private val scope: CoroutineScope
) : PageKeyedDataSource<Int, Movie>() {

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, Movie>
    ) {
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
        scope.launch(getJobErrorHandler()) {
            val response = remoteDataSource.getMovies(dateFrom, dateTo, page, pageSize)
            if (response.status == DataResult.Status.SUCCESS) {
                val results = response.data?.results ?: listOf()
                dataBaseDataSource.insertAll(results)
                callback(results)
            } else if (response.status == DataResult.Status.ERROR) {
                postError(response.error)
            }
        }
    }

    private fun getJobErrorHandler() = CoroutineExceptionHandler { _, e ->
        postError(e)
    }

    private fun postError(throwable: Throwable?) {
        //Timber.e("An error happened: $message")
        // TODO network error handling
        //networkState.postValue(NetworkState.FAILED)
    }

}