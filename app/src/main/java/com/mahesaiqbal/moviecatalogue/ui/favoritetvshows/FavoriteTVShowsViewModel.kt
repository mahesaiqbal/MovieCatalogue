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

    var category: MutableLiveData<String> = MutableLiveData()

    var favTVShows = Transformations.switchMap<String, Resource<PagedList<ResultTVShowEntity>>>(
        category
    ) { movieRepository.getAllFavoriteTVShows() }

    fun setCategory(category: String) {
        this.category.postValue(category)
    }

    fun setFavorite(tvShow: ResultTVShowEntity) {
        val newState = !tvShow.favorited
        movieRepository.setTVShowFavorite(tvShow, newState)
    }
}
