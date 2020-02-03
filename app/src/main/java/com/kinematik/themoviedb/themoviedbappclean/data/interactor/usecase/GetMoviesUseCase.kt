package com.kinematik.themoviedb.themoviedbappclean.data.interactor.usecase

import com.kinematik.themoviedb.app.core.domain.Movies
import com.kinematik.themoviedb.themoviedbappclean.data.datasource.LocalDataBaseDataSource
import com.kinematik.themoviedb.themoviedbappclean.presentation.network.RetrofitRemoteDataSource

class GetMoviesUseCase constructor(val remoteDataSource: RetrofitRemoteDataSource, val localDataBaseDataSource: LocalDataBaseDataSource) {

}