package com.example.movieappv4.ui.movielist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.movieappv4.R
import com.example.movieappv4.databinding.FragmentMovieListBinding
import com.example.movieappv4.domain.model.DomainMovie
import com.example.movieappv4.ui.movielist.adapter.MovieListAdapter
import com.example.movieappv4.ui.navigator.Navigator
import com.example.movieappv4.utils.hide
import com.example.movieappv4.utils.show
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.launch
import javax.inject.Inject

@ExperimentalCoroutinesApi
@FlowPreview
@AndroidEntryPoint
class MovieListFragment : Fragment() {

    var binding: FragmentMovieListBinding? = null

    private val viewModel by viewModels<MovieListViewModel>()

    @Inject
    lateinit var navigator: Navigator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        showMovieList()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        if (binding == null) {
            binding = FragmentMovieListBinding.inflate(inflater, container, false)
        }
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        showLoading()
        showMovieList()
    }

    private fun showLoading() {
        binding?.progressBar?.show()
    }

    private fun hideLoading() {
        binding?.progressBar?.hide()
    }

    private fun showError() {
        binding?.root?.let {
            val snackbar = Snackbar
                .make(
                    it,
                    getString(R.string.error_get_movie_list),
                    Snackbar.LENGTH_LONG
                )
            snackbar.show()
        }
    }

    private fun showMovieList() {
        viewModel.movieList.observe(this, Observer { response ->
            response?.let { movieList ->
                hideLoading()
                val adapter = MovieListAdapter(movieList) {
                    onItemCharacterTapped(it)
                }
                binding?.apply {
                    mainRecycler.adapter = adapter
                    mainRecycler.show()
                }
            }
        })
    }

    private fun onItemCharacterTapped(domainMovie: DomainMovie) {
        binding?.let {
            navigator.goToMovieDetail(it.root, domainMovie)
        }
    }

    private fun goToCharacterEdit(domainMovie: DomainMovie) {
        binding?.let {
            navigator.goToMovieDetail(it.root, domainMovie)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}
