package com.example.movieappv4.ui.movielist.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.movieappv4.databinding.ViewItemMovieBinding
import com.example.movieappv4.domain.model.DomainMovie

class MovieListAdapter(
    private val items: List<DomainMovie>,
    private val onItemClickListener: (DomainMovie) -> Unit
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding =
            ViewItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieViewHolder(binding)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val summary = items[position]
        (holder as MovieViewHolder).bind(summary, onItemClickListener)
    }
}
