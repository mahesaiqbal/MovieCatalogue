package com.mahesaiqbal.moviecatalogue.ui.favoritetvshows

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
import com.mahesaiqbal.moviecatalogue.data.source.local.entity.tvshowentity.ResultTVShowEntity
import com.mahesaiqbal.moviecatalogue.ui.favoritetvshows.FavoriteTVShowsPagedAdapter.FavoriteTVShowsViewHolder
import kotlinx.android.synthetic.main.item_tv_show_favorited.view.*

class FavoriteTVShowsPagedAdapter(var callback: FavoriteTVShowCallback) :
    PagedListAdapter<ResultTVShowEntity, FavoriteTVShowsViewHolder>(DIFF_CALLBACK) {

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<ResultTVShowEntity>() {
            override fun areItemsTheSame(oldItem: ResultTVShowEntity, newItem: ResultTVShowEntity): Boolean {
                return oldItem.id == newItem.id
            }

            @SuppressLint("DiffUtilEquals")
            override fun areContentsTheSame(oldItem: ResultTVShowEntity, newItem: ResultTVShowEntity): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteTVShowsViewHolder =
        FavoriteTVShowsViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_movie_favorited, parent, false))

    override fun onBindViewHolder(holder: FavoriteTVShowsViewHolder, position: Int) {
        val tvShow: ResultTVShowEntity = getItem(position)!!
        holder.bindItem(tvShow)
    }

    fun getItemById(swipedPosition: Int): ResultTVShowEntity = getItem(swipedPosition)!!

    inner class FavoriteTVShowsViewHolder(itemView: View) : ViewHolder(itemView) {

        fun bindItem(tvShow: ResultTVShowEntity) {
            itemView.tv_title.text = tvShow.name
            itemView.tv_overview.text = tvShow.overview
            itemView.tv_release_date.text = tvShow.firstAirDate

            itemView.setOnClickListener { callback.onItemClick(tvShow) }

            Glide.with(itemView.context)
                .load("https://image.tmdb.org/t/p/w500${tvShow.posterPath}")
                .override(500, 500)
                .apply(RequestOptions.placeholderOf(R.drawable.ic_movie).error(R.drawable.ic_error))
                .into(itemView.img_poster)
        }
    }

    interface FavoriteTVShowCallback {
        fun onItemClick(tvShow: ResultTVShowEntity)
    }
}
