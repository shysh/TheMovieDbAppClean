package com.kinematik.themoviedb.themoviedbappclean.farmework.network.mapper

import com.kinematik.themoviedb.app.core.domain.MoviesResponse
import com.kinematik.themoviedb.domain.common.BaseMapper
import com.kinematik.themoviedb.domain.entity.Movie
import com.kinematik.themoviedb.domain.entity.Page
import javax.inject.Inject

class MoviesResponseMapper @Inject constructor(private val movieResponseMapper: MovieResponseMapper): BaseMapper<Page<Movie>, MoviesResponse> {


    override fun mapFromEntity(type: Page<Movie>): MoviesResponse {
        return MoviesResponse(page = type.page,
            total_results = type.total_results,
            total_pages = type.total_pages)
    }

    override fun mapToEntity(type: MoviesResponse): Page<Movie> {

        return Page<Movie>(
            page = type.page,
            total_pages = type.total_pages,
            total_results = type.total_results,
            results = type.results.map {
                movieResponseMapper.mapToEntity(it)
            }
        )

    }
}