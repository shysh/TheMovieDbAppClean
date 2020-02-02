package com.kinematik.themoviedb.themoviedbappclean.domain.entity

data class User(val name:String,
                val email:String,
                val imageUrl:String? = null) {
}