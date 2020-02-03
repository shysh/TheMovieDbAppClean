package com.kinematik.themoviedb.data.datasource

import com.kinematik.themoviedb.domain.entity.User

interface CacheDataSource {

    suspend fun getUserData(): User?

    suspend fun saveUserData(user:User):Boolean

    suspend fun removeUserData():Boolean
}