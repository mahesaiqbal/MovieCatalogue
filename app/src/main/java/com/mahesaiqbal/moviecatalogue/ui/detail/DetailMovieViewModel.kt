package com.mahesaiqbal.moviecatalogue.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.mahesaiqbal.moviecatalogue.data.source.MovieRepository
import com.mahesaiqbal.moviecatalogue.data.source.remote.RemoteRepository
import com.mahesaiqbal.moviecatalogue.data.source.remote.response.detailmovie.DetailMovie
import com.mahesaiqbal.moviecatalogue.utils.DataDummy

class DetailMovieViewModel(var movieRepository: MovieRepository) : ViewModel() {

    private val remoteRepository = RemoteRepository()

    var movieId: Int? = null

    fun setMovieIdValue(id: Int) {
        movieId = id
    }

    fun getMovieIdValue(): Int {
        return movieId!!
    }

    fun getMovie(): LiveData<DetailMovie> = movieRepository.getDetailMovie(movieId!!)

    override fun onCleared() {
        super.onCleared()
        remoteRepository.onDestroy()
    }
}