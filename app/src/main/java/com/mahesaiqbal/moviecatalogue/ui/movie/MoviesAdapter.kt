package com.mahesaiqbal.moviecatalogue.ui.movie

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.mahesaiqbal.moviecatalogue.R
import com.mahesaiqbal.moviecatalogue.data.source.remote.response.movies.ResultMovie
import com.mahesaiqbal.moviecatalogue.ui.movie.MoviesAdapter.MoviesViewHolder
import kotlinx.android.synthetic.main.item_movie.view.*

class MoviesAdapter(var activity: Activity, var movies: MutableList<ResultMovie>, var callback: MoviesFragmentCallback) : Adapter<MoviesViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewHolder
            = MoviesViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false))

    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
        holder.bindItem(movies[position], callback)
    }

    override fun getItemCount(): Int = movies.size

    inner class MoviesViewHolder(itemView: View) : ViewHolder(itemView) {

        fun bindItem(movies: ResultMovie, callback: MoviesFragmentCallback) {
            itemView.tv_title.text = movies.title
            itemView.tv_overview.text = movies.overview
            itemView.tv_release_date.text = movies.releaseDate

            itemView.setOnClickListener { v -> callback.onItemClick(movies) }

            Glide.with(activity)
                .load(movies.posterPath)
                .override(500, 500)
                .apply(RequestOptions.placeholderOf(R.drawable.ic_movie).error(R.drawable.ic_error))
                .into(itemView.img_poster);
        }
    }

    interface MoviesFragmentCallback {
        fun onItemClick(movies: ResultMovie)
    }
}