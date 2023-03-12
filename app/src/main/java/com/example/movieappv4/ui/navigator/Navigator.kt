package com.example.movieappv4.ui.navigator

import android.view.View
import androidx.navigation.NavDirections
import androidx.navigation.findNavController
import com.example.movieappv4.domain.model.DomainMovie
import com.example.movieappv4.ui.movielist.MovieListFragmentDirections
import javax.inject.Inject

class Navigator @Inject constructor() {

    fun goToMovieDetail(view: View, domainMovie: DomainMovie) {
        val direction =
            MovieListFragmentDirections.actionSlashFragmentToCharacterListFragment(domainMovie)
        safeNavigation(view, direction)
    }

    private fun safeNavigation(view: View, direction: NavDirections) {
        view.findNavController().currentDestination?.getAction(direction.actionId)
            ?.let { view.findNavController().navigate(direction) }
    }
}
