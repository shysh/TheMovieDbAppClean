package com.kinematik.themoviedb.themoviedbappclean.farmework.db.mapper

import com.kinematik.themoviedb.domain.entity.Movie
import com.kinematik.themoviedb.app.core.domain.MovieResponse
import com.kinematik.themoviedb.domain.common.BaseMapper
import com.kinematik.themoviedb.themoviedbappclean.farmework.db.entity.MovieDBEntity

class MovieDBEntityMapper:
    BaseMapper<Movie, MovieDBEntity> {

    override fun mapFromEntity(type: Movie): MovieDBEntity {
        return MovieDBEntity(
            popularity = type.popularity,
            vote_count = type.vote_count,
            video = type.video,
            poster_path = type.poster_path,
            id = type.id,
            adult = type.adult,
            backdrop_path = type.backdrop_path,
            original_language = type.original_language,
            original_title = type.original_title,
            title = type.title,
            vote_average = type.vote_average,
            overview = type.overview,
            release_date = type.release_date,
            homepage = type.homepage
            )
    }

    override fun mapToEntity(type: MovieDBEntity): Movie {
        return Movie(
            popularity = type.popularity,
            vote_count = type.vote_count,
            video = type.video,
            poster_path = type.poster_path,
            id = type.id,
            adult = type.adult,
            backdrop_path = type.backdrop_path,
            original_language = type.original_language,
            original_title = type.original_title,
            title = type.title,
            vote_average = type.vote_average,
            overview = type.overview,
            release_date = type.release_date,
            homepage = type.homepage
        )
    }
}