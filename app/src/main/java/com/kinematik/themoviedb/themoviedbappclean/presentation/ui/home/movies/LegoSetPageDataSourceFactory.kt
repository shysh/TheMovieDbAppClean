package com.kinematik.themoviedb.themoviedbappclean.presentation.ui.home.movies

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import androidx.paging.PagedList
import com.kinematik.themoviedb.data.datasource.DataBaseDataSource
import com.kinematik.themoviedb.data.datasource.RemoteDataSource
import com.kinematik.themoviedb.domain.entity.Movie
import kotlinx.coroutines.CoroutineScope

class LegoSetPageDataSourceFactory constructor(
    private val dateFrom: String,
    private val dateTo: String,
    private val page: Int = 1,
    private val pageSize: Int = 20,
    private val remoteDataSource: RemoteDataSource,
    private val dataBaseDataSource: DataBaseDataSource,
    private val scope: CoroutineScope
) : DataSource.Factory<Int, Movie>() {

    private val liveData = MutableLiveData<LegoSetPageDataSource>()

    override fun create(): DataSource<Int, Movie> {
        val source = LegoSetPageDataSource(dateFrom,dateTo,page, pageSize, remoteDataSource, dataBaseDataSource, scope)
        liveData.postValue(source)
        return source
    }

    companion object {
        private const val PAGE_SIZE = 100

        fun pagedListConfig() = PagedList.Config.Builder()
            .setInitialLoadSizeHint(PAGE_SIZE)
            .setPageSize(PAGE_SIZE)
            .setEnablePlaceholders(true)
            .build()
    }

}