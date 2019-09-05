package com.mahesaiqbal.moviecatalogue.ui.tvshow

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.mahesaiqbal.moviecatalogue.data.source.MovieRepository
import com.mahesaiqbal.moviecatalogue.data.source.local.entity.tvshowentity.ResultTVShowEntity
import com.mahesaiqbal.moviecatalogue.data.source.remote.RemoteRepository
import com.mahesaiqbal.moviecatalogue.data.source.remote.response.tvshows.ResultTVShows
import com.mahesaiqbal.moviecatalogue.utils.DataDummy
import com.mahesaiqbal.moviecatalogue.vo.Resource

class TVShowsViewModel(var movieRepository: MovieRepository) : ViewModel() {

    private val remoteRepository = RemoteRepository()

    var tvShow = MutableLiveData<MutableList<ResultTVShows>>()

    var category: MutableLiveData<String> = MutableLiveData()

//    fun getAllTVShows(): MutableLiveData<MutableList<ResultTVShows>> = movieRepository.getAllTVShows()

    var tvShows = Transformations.switchMap<String, Resource<MutableList<ResultTVShowEntity>>>(
        category
    ) { movieRepository.getAllTVShows() }

    fun getTVShows() {
//        tvShow = movieRepository.getAllTVShows()
    }

    fun setCategory(category: String) {
        this.category.postValue(category)
    }

    override fun onCleared() {
        super.onCleared()
        remoteRepository.onDestroy()
    }
}