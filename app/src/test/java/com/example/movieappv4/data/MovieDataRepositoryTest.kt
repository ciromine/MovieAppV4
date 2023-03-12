package com.example.movieappv4.data

import com.example.movieappv4.data.mapper.DataResponseMapper
import com.example.movieappv4.data.remote.model.MovieListResponse
import com.example.movieappv4.data.remote.model.MovieResponse
import com.example.movieappv4.data.source.MovieRemote
import com.example.movieappv4.domain.model.DomainMovie
import com.example.movieappv4.domain.model.DomainMovieList
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Test


@InternalCoroutinesApi
@ExperimentalCoroutinesApi
class MovieDataRepositoryTest {
    private val remote = mockk<MovieRemote>()
    private val dataResponseMapper = mockk<DataResponseMapper>()

    private val repository = MovieDataRepository(remote, dataResponseMapper)

    private val remoteMovieListResponse = MovieListResponse(
        results = listOf(MovieResponse("","","","","","",))
    )

    private val domainMovieList = DomainMovieList(
        results = listOf(DomainMovie("","","","","","",))
    )

    @Test
    fun `given remote when getMovieList then Completes`() = runBlocking {
        stubGetMovieList(remoteMovieListResponse)
        stubDataResponseMapper(remoteMovieListResponse, domainMovieList)

        repository.getMovieList().collect { result->
            assertEquals(domainMovieList, result)
        }

        coVerify { remote.getMovieList() }
    }

    private fun stubGetMovieList(remoteMovieListResponse: MovieListResponse) {
        coEvery { remote.getMovieList() } returns remoteMovieListResponse
    }

    private fun stubDataResponseMapper(
        remote: MovieListResponse,
        domain: DomainMovieList
    ) {
        every { with(dataResponseMapper) { remote.toDomain() } } returns domain
    }
}
