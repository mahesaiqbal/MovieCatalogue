package com.mahesaiqbal.moviecatalogue.ui.favoritetvshows

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.mahesaiqbal.moviecatalogue.data.source.MovieRepository
import com.mahesaiqbal.moviecatalogue.data.source.local.entity.tvshowentity.ResultTVShowEntity
import com.mahesaiqbal.moviecatalogue.vo.Resource

class FavoriteTVShowsViewModel(var movieRepository: MovieRepository) : ViewModel() {

    fun getAllFavoriteTVShows(): LiveData<Resource<PagedList<ResultTVShowEntity>>> = movieRepository.getAllFavoriteTVShows()

    fun setFavorite(tvShow: ResultTVShowEntity) {
        val newState = !tvShow.favorited
        movieRepository.setTVShowFavorite(tvShow, newState)
    }
}
