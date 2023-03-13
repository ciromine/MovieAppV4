package com.example.movieappv4.domain

import com.example.movieappv4.core.Resource
import com.example.movieappv4.domain.model.DomainMovie
import com.example.movieappv4.domain.model.DomainMovieList
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import org.junit.Test

@ExperimentalCoroutinesApi
internal class GetMovieListUseCaseTest {
    private val getMovieListUseCase = mockk<GetMovieListUseCase>(relaxed = true)

    private val stubDomainMovie = DomainMovie("","","","","","")

    private val stubDomainMovieList = DomainMovieList(
        results = listOf(stubDomainMovie)
    )

    @Test
    fun `given getMovieListUseCase and use case with DomainMovieList, then return Success`() =
        runBlocking {
            stubGetMovieListUseCase(Resource.Success(stubDomainMovieList))

            val results = getMovieListUseCase.invoke().first().data?.results

            assertEquals(results, stubDomainMovieList.results)
        }

    private fun stubGetMovieListUseCase(domainMovieList: Resource<DomainMovieList>) {
        coEvery { getMovieListUseCase.invoke() } returns flow { emit(domainMovieList) }
    }
}