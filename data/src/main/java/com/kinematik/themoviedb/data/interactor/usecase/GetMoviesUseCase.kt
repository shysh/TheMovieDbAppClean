package com.kinematik.themoviedb.data.interactor.usecase


import com.kinematik.themoviedb.data.datasource.RemoteDataSource


class GetMoviesUseCase constructor(private val remoteDataSource: RemoteDataSource) {

    suspend operator fun invoke(
        dateFrom: String,
        dateTo: String,
        page: Int = 0,
        pageSize: Int = 20
    ) = remoteDataSource.getMovies(
        dateFrom, dateTo, page, pageSize
    )
}


