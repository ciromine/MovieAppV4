package com.example.movieappv4.ui.movielist

import com.example.movieappv4.domain.model.DomainMovie

data class MovieListState(
    val isLoading: Boolean = false,
    val users: List<DomainMovie> = emptyList(),
    val error: String = ""
)