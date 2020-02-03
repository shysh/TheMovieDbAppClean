package com.kinematik.themoviedb.data

import com.kinematik.themoviedb.data.datasource.CacheDataSource
import com.kinematik.themoviedb.data.datasource.LocalDataBaseDataSource
import com.kinematik.themoviedb.data.datasource.RemoteDataSource

class Repository(
    val networkDataSource: RemoteDataSource,
    val localDataBaseDataSource: LocalDataBaseDataSource,
    val cacheDataSource: CacheDataSource
)