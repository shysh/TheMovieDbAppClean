package com.kinematik.themoviedb.domain.common

interface BaseMapper<E, D> {

    fun mapFromEntity(type: E): D

    fun mapToEntity(type: D): E
}