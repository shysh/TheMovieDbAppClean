package com.kinematik.themoviedb.themoviedbappclean.farmework.cache.mapper

import com.kinematik.themoviedb.domain.common.BaseMapper
import com.kinematik.themoviedb.domain.entity.User
import com.kinematik.themoviedb.themoviedbappclean.farmework.cache.entity.UserCacheEntity

class UserCacheEntityMapper : BaseMapper<User, UserCacheEntity> {

    override fun mapFromEntity(type: User): UserCacheEntity {
        return UserCacheEntity(
            type.firtName,
            type.lastName,
            type.email,
            type.imageUrl
        )
    }

    override fun mapToEntity(type: UserCacheEntity): User {
        return User(
            type.firstName,
            type.lastName,
            type.email,
            type.imageUrl
        )
    }
}