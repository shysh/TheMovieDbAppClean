package com.kinematik.themoviedb.data

import com.kinematik.themoviedb.data.datasource.CacheDataSource
import com.kinematik.themoviedb.data.datasource.DataBaseDataSource
import com.kinematik.themoviedb.data.datasource.RemoteDataSource

open class Repository(
    val networkDataSource: RemoteDataSource,
    val localDataBaseDataSource: DataBaseDataSource,
    val cacheDataSource: CacheDataSource
)