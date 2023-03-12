package com.example.movieappv4.data.mapper

import com.example.movieappv4.data.remote.model.MovieListResponse
import com.example.movieappv4.data.remote.model.MovieResponse
import com.example.movieappv4.domain.model.DomainMovie
import com.example.movieappv4.domain.model.DomainMovieList
import javax.inject.Inject

class DataResponseMapper @Inject constructor() {

    fun MovieListResponse.toDomain() = DomainMovieList(
        results = results.map { it.toDomainItem() }
    )

    private fun MovieResponse.toDomainItem() = DomainMovie(
        title = title,
        posterPath = posterPath,
        backdropPath = backdropPath,
        originalTitle = originalTitle,
        overview = overview,
        releaseDate = releaseDate
    )
}
