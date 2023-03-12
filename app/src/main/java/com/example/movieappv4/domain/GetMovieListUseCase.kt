package com.example.movieappv4.domain

import com.example.movieappv4.core.Resource
import com.example.movieappv4.domain.model.DomainMovie
import com.example.movieappv4.domain.model.DomainMovieList
import com.example.movieappv4.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetMovieListUseCase
@Inject
constructor(private val repository: MovieRepository) {
    operator fun invoke(): Flow<Resource<DomainMovieList>> = flow {
        try {
            emit(Resource.Loading<DomainMovieList>())
            val movieList = repository.getMovieList().first()
            emit(Resource.Success<DomainMovieList>(movieList))
        } catch(e: HttpException) {
            emit(Resource.Error<DomainMovieList>(e.localizedMessage ?: "An unexpected error occured"))
        } catch(e: IOException) {
            emit(Resource.Error<DomainMovieList>("Couldn't reach server. Check your internet connection."))
        }
    }
}
