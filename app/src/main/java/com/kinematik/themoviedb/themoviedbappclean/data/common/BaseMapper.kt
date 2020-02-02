package com.kinematik.themoviedb.themoviedbappclean.data.common

interface BaseMapper<E, D> {

    fun mapFromEntity(type: E): D

    fun mapToEntity(type: D): E
}