package com.kinematik.themoviedb.themoviedbappclean.presentation.ui.home.movies

import android.util.Log
import androidx.paging.PageKeyedDataSource
import com.kinematik.themoviedb.data.datasource.DataBaseDataSource
import com.kinematik.themoviedb.data.datasource.RemoteDataSource
import com.kinematik.themoviedb.domain.common.DataResult
import com.kinematik.themoviedb.themoviedbappclean.presentation.common.mapper.MoviePresentationMapper
import com.kinematik.themoviedb.themoviedbappclean.presentation.common.model.MoviePresentationDao
import kotlinx.coroutines.*
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
    private val mapper: MoviePresentationMapper,
    private val scope: CoroutineScope
) : PageKeyedDataSource<Int, MoviePresentationDao>() {

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, MoviePresentationDao>
    ) {
        fetchData(1, params.requestedLoadSize) {
            callback.onResult(it, null, 2)
        }
    }

    override fun loadAfter(
        params: LoadParams<Int>,
        callback: LoadCallback<Int, MoviePresentationDao>
    ) {
        val page = params.key
        fetchData(page, params.requestedLoadSize) {
            callback.onResult(it, page + 1)
        }
    }

    override fun loadBefore(
        params: LoadParams<Int>,
        callback: LoadCallback<Int, MoviePresentationDao>
    ) {
        val page = params.key
        fetchData(page, params.requestedLoadSize) {
            callback.onResult(it, page - 1)
        }
    }

    private fun fetchData(
        page: Int,
        pageSize: Int,
        callback: (List<MoviePresentationDao>) -> Unit
    ) {
        scope.launch(getJobErrorHandler()) {
            withContext(Dispatchers.IO) {
                Log.d("TUTUTU", Thread.currentThread().name)
                val response = remoteDataSource.getMovies(dateFrom, dateTo, page, pageSize)
                if (response.status == DataResult.Status.SUCCESS) {
                    val results = response.data?.results ?: listOf()
                    dataBaseDataSource.insertAll(results)
                    callback(results.map { mapper.mapFromEntity(it) })
                } else if (response.status == DataResult.Status.ERROR) {
                    postError(response.error)
                }
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