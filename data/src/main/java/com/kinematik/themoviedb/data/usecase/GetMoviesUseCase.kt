package com.kinematik.themoviedb.data.usecase


import com.kinematik.themoviedb.data.datasource.DataBaseDataSource
import com.kinematik.themoviedb.data.datasource.RemoteDataSource

class GetMoviesUseCase constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataBaseDataSource: DataBaseDataSource) {

    suspend operator fun invoke(
        dateFrom: String,
        dateTo: String,
        page: Int = 0,
        pageSize: Int = 20
    ) = remoteDataSource.getMovies(
        dateFrom, dateTo, page, pageSize
    )
}


