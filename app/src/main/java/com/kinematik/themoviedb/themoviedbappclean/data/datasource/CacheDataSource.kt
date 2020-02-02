package com.kinematik.themoviedb.themoviedbappclean.data.datasource

import com.kinematik.themoviedb.themoviedbappclean.domain.entity.User

interface CacheDataSource {

    suspend fun getUserData(): User?

    suspend fun saveUserData(user:User):Boolean

    suspend fun removeUserData():Boolean
}