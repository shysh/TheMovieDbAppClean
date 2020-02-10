package com.kinematik.themoviedb.themoviedbappclean.farmework.cache

import com.kinematik.themoviedb.data.datasource.CacheDataSource
import com.kinematik.themoviedb.domain.entity.User
import com.kinematik.themoviedb.themoviedbappclean.farmework.cache.entity.UserCacheEntity
import com.kinematik.themoviedb.themoviedbappclean.farmework.cache.mapper.UserCacheEntityMapper
import javax.inject.Inject

class FileBasedCacheDataSource @Inject constructor(private val cacheManager: FileBasedCacheManager): CacheDataSource {


    val mapper = UserCacheEntityMapper()

    override suspend fun getUserData(): User? {
        return cacheManager.getUserData()?.let { _user ->
            mapper.mapToEntity(_user)
        }
    }

    override suspend fun saveUserData(user: User): Boolean {
        getUserData()?.let {
            removeUserData()
        }
        return cacheManager.saveUserData(mapper.mapFromEntity(user))

    }

    override suspend fun removeUserData(): Boolean {
        return cacheManager.removeUserData()
    }
}