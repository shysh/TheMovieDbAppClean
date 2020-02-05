package com.kinematik.themoviedb.themoviedbappclean.farmework.interactor

import com.kinematik.themoviedb.data.interactor.MoviesInteractor
import com.kinematik.themoviedb.themoviedbappclean.farmework.db.RoomLocalDataBaseDataSource
import com.kinematik.themoviedb.themoviedbappclean.farmework.network.RetrofitRemoteDataSource
import javax.inject.Inject

class MoviesInteractorImp @Inject constructor(retrofitRemoteDataSource: RetrofitRemoteDataSource,
                                              roomLocalDataBaseDataSource: RoomLocalDataBaseDataSource)
    :MoviesInteractor(retrofitRemoteDataSource, roomLocalDataBaseDataSource) {
}