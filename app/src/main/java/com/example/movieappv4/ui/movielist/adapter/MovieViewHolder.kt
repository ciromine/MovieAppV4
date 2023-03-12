package com.example.movieappv4.ui.movielist.adapter

import androidx.recyclerview.widget.RecyclerView
import com.example.movieappv4.databinding.ViewItemMovieBinding
import com.example.movieappv4.domain.model.DomainMovie
import com.example.movieappv4.utils.Constants
import com.squareup.picasso.Picasso

class MovieViewHolder(val binding: ViewItemMovieBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(attrs: DomainMovie, onClickListener: (DomainMovie) -> Unit) {
        binding.apply {
            title.text = attrs.title
            root.setOnClickListener { onClickListener.invoke(attrs) }
            Picasso.get().load(Constants.baseUrlImages + attrs.posterPath)
                .fit().centerCrop()
                .into(image)
        }
    }
}
