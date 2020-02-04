package com.kinematik.themoviedb.themoviedbappclean.farmework.cache

import com.kinematik.themoviedb.data.datasource.CacheDataSource
import com.kinematik.themoviedb.domain.entity.User

class SharedPreferencesCacheDataSource(): CacheDataSource {

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