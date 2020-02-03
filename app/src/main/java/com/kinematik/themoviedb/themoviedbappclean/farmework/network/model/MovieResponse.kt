package com.kinematik.themoviedb.app.core.domain

import com.google.gson.annotations.SerializedName

data class MovieResponse(
    @SerializedName("id") val id: Int = 0,
    @SerializedName("popularity") val popularity: Float = 0.0f,
    @SerializedName("vote_count") val vote_count: Int = 0,
    @SerializedName("video") val video: Boolean = false,
    @SerializedName("poster_path") val poster_path: String? = null,
    @SerializedName("adult") val adult: Boolean = false,
    @SerializedName("backdrop_path") val backdrop_path: String? = null,
    @SerializedName("original_language") val original_language: String = "en",
    @SerializedName("original_title") val original_title: String? = null,
    @SerializedName("title") val title: String? = null,
    @SerializedName("vote_average") val vote_average: Float = 0.0f,
    @SerializedName("overview") val overview: String? = null,
    @SerializedName("release_date") val release_date: String? = null,
    @SerializedName("homepage") val homepage:String? = null
)