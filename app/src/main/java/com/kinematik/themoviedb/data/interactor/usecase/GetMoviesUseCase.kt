package com.kinematik.themoviedb.data.interactor.usecase

import com.kinematik.themoviedb.data.datasource.LocalDataBaseDataSource
import com.kinematik.themoviedb.themoviedbappclean.farmework.network.RetrofitRemoteDataSource

class GetMoviesUseCase constructor(val remoteDataSource: RetrofitRemoteDataSource, val localDataBaseDataSource: LocalDataBaseDataSource) {

}