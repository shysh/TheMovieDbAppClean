package com.kinematik.themoviedb.themoviedbappclean.farmework.cache

import com.kinematik.themoviedb.data.datasource.CacheDataSource
import com.kinematik.themoviedb.domain.entity.User
import com.kinematik.themoviedb.themoviedbappclean.farmework.cache.entity.UserCacheEntity
import com.kinematik.themoviedb.themoviedbappclean.farmework.cache.mapper.UserCacheEntityMapper
import javax.inject.Inject

class FileBasedCacheDataSource @Inject constructor(private val cacheManager: FileBasedCacheManager): CacheDataSource {

    //todo mapper
    val mapper = UserCacheEntityMapper()

    override suspend fun getUserData(): User? {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override suspend fun saveUserData(user: User): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override suspend fun removeUserData(): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}