package com.kinematik.themoviedb.themoviedbappclean.farmework

import com.kinematik.themoviedb.data.Repository
import com.kinematik.themoviedb.themoviedbappclean.farmework.cache.FileBasedCacheDataSource
import com.kinematik.themoviedb.themoviedbappclean.farmework.db.RoomLocalDataBaseDataSource
import com.kinematik.themoviedb.themoviedbappclean.farmework.network.RetrofitRemoteDataSource
import javax.inject.Inject

class MoviesRepository @Inject constructor(remote: RetrofitRemoteDataSource, local: RoomLocalDataBaseDataSource, cache: FileBasedCacheDataSource):Repository(remote, local,cache) {
}