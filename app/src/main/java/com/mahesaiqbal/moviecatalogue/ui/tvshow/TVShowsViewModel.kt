package com.mahesaiqbal.moviecatalogue.ui.tvshow

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.mahesaiqbal.moviecatalogue.data.source.MovieRepository
import com.mahesaiqbal.moviecatalogue.data.source.remote.RemoteRepository
import com.mahesaiqbal.moviecatalogue.data.source.remote.response.tvshows.ResultTVShows
import com.mahesaiqbal.moviecatalogue.utils.DataDummy

class TVShowsViewModel(var movieRepository: MovieRepository) : ViewModel() {

    private val remoteRepository = RemoteRepository()

    fun getTVShows(): LiveData<MutableList<ResultTVShows>> = movieRepository.getAllTVShows()

    override fun onCleared() {
        super.onCleared()
        remoteRepository.onDestroy()
    }
}