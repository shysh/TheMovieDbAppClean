package com.kinematik.themoviedb.themoviedbappclean.farmework

import com.kinematik.themoviedb.data.Repository
import com.kinematik.themoviedb.themoviedbappclean.farmework.cache.FileBasedCacheDataSource
import com.kinematik.themoviedb.themoviedbappclean.farmework.db.RoomLocalDataBaseDataSource
import com.kinematik.themoviedb.themoviedbappclean.farmework.network.RetrofitRemoteDataSource
import javax.inject.Inject

class MoviesRepository @Inject constructor(cache: FileBasedCacheDataSource, remote: RetrofitRemoteDataSource, local: RoomLocalDataBaseDataSource):Repository(cache, remote, local) {
}