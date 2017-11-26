package com.sawrose.popularmovies.ui.adapter

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.sawrose.popularmovies.R
import com.sawrose.popularmovies.base.ViewType
import com.sawrose.popularmovies.base.ViewTypeAdapter
import com.sawrose.popularmovies.model.Movie
import com.sawrose.popularmovies.utils.GlideHelper
import com.sawrose.popularmovies.utils.inflate
import kotlinx.android.synthetic.main.view_movie.view.*


class MovieViewTypeAdapter(val onClick: (Movie) -> Unit) : ViewTypeAdapter {

    override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        return MovieViewHolder(parent.inflate(R.layout.view_movie, false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, item: ViewType) {
        holder as MovieViewHolder
        holder.bindView(item as Movie, onClick)
    }

    inner class MovieViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        fun bindView(movie: Movie, onClick: (Movie) -> Unit) = with(itemView) {
            GlideHelper.loadPoster(context, movie.poster_path, poster)
            setOnClickListener {
                onClick(movie)
            }
        }
    }
}