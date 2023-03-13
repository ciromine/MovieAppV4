package com.example.movieappv4.domain

import com.example.movieappv4.core.Resource
import com.example.movieappv4.domain.model.DomainMovieList
import com.example.movieappv4.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetMovieListUseCase
@Inject
constructor(private val repository: MovieRepository) {
    operator fun invoke(): Flow<Resource<DomainMovieList>> = flow {
        try {
            val movieList = repository.getMovieList().first()
            emit(Resource.Success(movieList))
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occured"))
        } catch (e: IOException) {
            emit(Resource.Error("Couldn't reach server. Check your internet connection."))
        }
    }
}
