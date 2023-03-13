package com.example.movieappv4.ui.movielist

import androidx.lifecycle.*
import com.example.movieappv4.core.Resource
import com.example.movieappv4.domain.GetMovieListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@FlowPreview
@ExperimentalCoroutinesApi
@HiltViewModel
class MovieListViewModel @Inject constructor(
    private val useCase: GetMovieListUseCase
) : ViewModel(), LifecycleObserver {

    private var _movieListMutable = MutableLiveData<MovieListResult>()
    val movieList: LiveData<MovieListResult> = _movieListMutable

    init {
        getMovieList()
    }

    private fun getMovieList() {
        useCase().onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _movieListMutable.value = MovieListResult(results = result.data?.results, error = false)
                }
                is Resource.Error -> {
                    _movieListMutable.value = MovieListResult(results = emptyList(), error = true)
                }
            }
        }.launchIn(viewModelScope)
    }
}