package com.mahesaiqbal.moviecatalogue.ui.tvshow

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.mahesaiqbal.moviecatalogue.R
import com.mahesaiqbal.moviecatalogue.data.source.local.entity.tvshowentity.ResultTVShowEntity
import com.mahesaiqbal.moviecatalogue.data.source.remote.response.tvshows.ResultTVShows
import com.mahesaiqbal.moviecatalogue.ui.tvshow.TVShowsAdapter.TVShowsViewHolder
import kotlinx.android.synthetic.main.item_tv_shows.view.*

class TVShowsAdapter(var activity: Activity, var tvShows: MutableList<ResultTVShowEntity>, var callback: TVShowsFragmentCallback) : Adapter<TVShowsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TVShowsViewHolder
            = TVShowsViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_tv_shows, parent, false))

    override fun onBindViewHolder(holder: TVShowsViewHolder, position: Int) {
        holder.bindItem(tvShows[position], callback)
    }

    override fun getItemCount(): Int = tvShows.size

    inner class TVShowsViewHolder(itemView: View) : ViewHolder(itemView) {

        fun bindItem(tvShows: ResultTVShowEntity, callback: TVShowsFragmentCallback) {
            itemView.tv_title.text = tvShows.name
            itemView.tv_overview.text = tvShows.overview
            itemView.tv_release_date.text = tvShows.firstAirDate

            itemView.setOnClickListener { v -> callback.onItemClick(tvShows) }

            Glide.with(activity)
                .load("https://image.tmdb.org/t/p/w500${tvShows.posterPath}")
                .override(500, 500)
                .apply(RequestOptions.placeholderOf(R.drawable.ic_movie).error(R.drawable.ic_error))
                .into(itemView.img_poster);
        }
    }

    interface TVShowsFragmentCallback {
        fun onItemClick(tvShows: ResultTVShowEntity)
    }
}