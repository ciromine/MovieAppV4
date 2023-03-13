package com.example.movieappv4.ui.movielist

import com.example.movieappv4.domain.model.DomainMovie

data class MovieListResult(
    var results: List<DomainMovie>?,
    var error: Boolean = false
)