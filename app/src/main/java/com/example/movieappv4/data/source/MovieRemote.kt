package com.example.movieappv4.data.source

import com.example.movieappv4.data.remote.model.MovieListResponse

interface MovieRemote {

    suspend fun getMovieList(): MovieListResponse
}
