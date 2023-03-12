package com.example.movieappv4.data.remote

import com.example.movieappv4.data.remote.model.MovieListResponse
import com.example.movieappv4.utils.Constants
import retrofit2.http.GET

interface MovieApi {

    @GET(Constants.getMovies + "?api_key=${Constants.apiKey}")
    suspend fun getMovies(): MovieListResponse
}
