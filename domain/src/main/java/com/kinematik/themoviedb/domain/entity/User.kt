package com.kinematik.themoviedb.domain.entity

data class User(val firtName:String?,
                val lastName:String?,
                val email:String?,
                val imageUrl:String? = null) {
}