package com.kinematik.themoviedb.themoviedbappclean.presentation.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "movies")
data class MovieDBEntity(
    @PrimaryKey
    @field:SerializedName("id") val id: Int,
    @field:SerializedName("popularity") val popularity: Float = 0.0f,
    @field:SerializedName("vote_count") val vote_count: Int = 0,
    @field:SerializedName("video") val video: Boolean = false,
    @field:SerializedName("poster_path") val poster_path: String? = null,
    @field:SerializedName("adult") val adult: Boolean = false,
    @field:SerializedName("backdrop_path") val backdrop_path: String? = null,
    @field:SerializedName("original_language") val original_language: String = "en",
    @field:SerializedName("original_title") val original_title: String? = null,
    @field:SerializedName("title") val title: String? = null,
    @field:SerializedName("vote_average") val vote_average: Float = 0.0f,
    @field:SerializedName("overview") val overview: String? = null,
    @field:SerializedName("release_date") val release_date: String? = null,
    @field:SerializedName("homepage") val homepage: String? = null
)