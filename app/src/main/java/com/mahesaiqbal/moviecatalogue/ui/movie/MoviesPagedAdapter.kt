package com.mahesaiqbal.moviecatalogue.ui.movie

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.mahesaiqbal.moviecatalogue.R
import com.mahesaiqbal.moviecatalogue.data.source.local.entity.movieentity.ResultMovieEntity
import com.mahesaiqbal.moviecatalogue.ui.movie.MoviesPagedAdapter.MoviesViewHolder
import kotlinx.android.synthetic.main.item_movie.view.*

class MoviesPagedAdapter(var callback: MoviesFragmentCallback) :
    PagedListAdapter<ResultMovieEntity, MoviesViewHolder>(DIFF_CALLBACK) {

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<ResultMovieEntity>() {
            override fun areItemsTheSame(oldItem: ResultMovieEntity, newItem: ResultMovieEntity): Boolean {
                return oldItem.id == newItem.id
            }

            @SuppressLint("DiffUtilEquals")
            override fun areContentsTheSame(oldItem: ResultMovieEntity, newItem: ResultMovieEntity): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewHolder =
        MoviesViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false))

    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
        val movie: ResultMovieEntity = getItem(position)!!
        holder.bindItem(movie, callback)
    }

    inner class MoviesViewHolder(itemView: View) : ViewHolder(itemView) {

        fun bindItem(movie: ResultMovieEntity, callback: MoviesFragmentCallback) {
            itemView.tv_title.text = movie.title
            itemView.tv_overview.text = movie.overview
            itemView.tv_release_date.text = movie.releaseDate

            itemView.setOnClickListener { callback.onItemClick(movie) }

            Glide.with(itemView.context)
                .load("https://image.tmdb.org/t/p/w500${movie.posterPath}")
                .override(500, 500)
                .apply(RequestOptions.placeholderOf(R.drawable.ic_movie).error(R.drawable.ic_error))
                .into(itemView.img_poster)
        }
    }

    interface MoviesFragmentCallback {
        fun onItemClick(movies: ResultMovieEntity)
    }
}