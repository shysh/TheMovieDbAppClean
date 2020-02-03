package com.kinematik.themoviedb.themoviedbappclean.farmework.network

import com.kinematik.themoviedb.app.core.domain.MoviesResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MoviesApiService {

    companion object CONSTANTS {
        const val ENDPOINT = "https://api.themoviedb.org/3"
        const val API_KEY = "7f6f9638f39efd41ac4c832d283f071f"
        const val QUERY_PARAM_API_KEY = "api_key"

    }

    @GET("/discover/movie")
    suspend fun discoverMovies(
        @Query("release_date.gte") fromDate: String,
        @Query("release_date.lte") toDate: String,
        @Query("page") page: Int
    ): Response<MoviesResponse>

    @GET("movie/{id}/")
    suspend fun getMovie(@Path("id") id: Int): Response<MoviesResponse>
}