package com.kinematik.themoviedb.themoviedbappclean.presentation.common.model

data class MoviePresentationDao (val id: Int = 0,
                                 val popularity: Float = 0.0f,
                                 val vote_count: Int = 0,
                                 val video: Boolean = false,
                                 val poster_path: String? = null,
                                 val adult: Boolean = false,
                                 val backdrop_path: String? = null,
                                 val original_language: String = "en",
                                 val original_title: String? = null,
                                 val title: String? = null,
                                 val vote_average: Float = 0.0f,
                                 val overview: String? = null,
                                 val release_date: String? = null,
                                 val homepage:String? = null)