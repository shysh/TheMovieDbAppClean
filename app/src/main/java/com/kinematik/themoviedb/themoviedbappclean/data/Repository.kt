package com.kinematik.themoviedb.themoviedbappclean.data

import com.kinematik.themoviedb.themoviedbappclean.data.datasource.CacheDataSource
import com.kinematik.themoviedb.themoviedbappclean.data.datasource.LocalDataBaseDataSource
import com.kinematik.themoviedb.themoviedbappclean.data.datasource.RemoteDataSource

class Repository(
    val networkDataSource: RemoteDataSource,
    val localDataBaseDataSource: LocalDataBaseDataSource,
    val cacheDataSource: CacheDataSource
)