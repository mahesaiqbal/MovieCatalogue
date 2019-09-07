package com.mahesaiqbal.moviecatalogue.ui.tvshow

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.mahesaiqbal.moviecatalogue.data.source.MovieRepository
import com.mahesaiqbal.moviecatalogue.data.source.local.entity.tvshowentity.ResultTVShowEntity
import com.mahesaiqbal.moviecatalogue.data.source.remote.RemoteRepository
import com.mahesaiqbal.moviecatalogue.vo.Resource

class TVShowsViewModel(var movieRepository: MovieRepository) : ViewModel() {

    private val remoteRepository = RemoteRepository()

    fun getAllTVShows(): LiveData<Resource<PagedList<ResultTVShowEntity>>> = movieRepository.getAllTVShows()

    override fun onCleared() {
        super.onCleared()
        remoteRepository.onDestroy()
    }
}