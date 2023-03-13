package com.example.movieappv4.ui.movielist

import androidx.lifecycle.*
import com.example.movieappv4.core.Resource
import com.example.movieappv4.domain.GetMovieListUseCase
import com.example.movieappv4.domain.model.DomainMovie
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

    private var _movieListMutable = MutableLiveData<List<DomainMovie>>()
    val movieList: LiveData<List<DomainMovie>> = _movieListMutable

    init {
        getMovieList()
    }

    private fun getMovieList() {
        useCase().onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _movieListMutable.value = result.data?.results
                }
                is Resource.Error -> {
                    result.message ?: "An unexpected error occurred"
                }
                is Resource.Loading -> {

                }
            }
        }.launchIn(viewModelScope)
    }
}