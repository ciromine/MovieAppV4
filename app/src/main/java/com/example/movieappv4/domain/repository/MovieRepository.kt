package com.example.movieappv4.domain.repository

import com.example.movieappv4.domain.model.DomainMovieList
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    fun getMovieList(): Flow<DomainMovieList>
}
