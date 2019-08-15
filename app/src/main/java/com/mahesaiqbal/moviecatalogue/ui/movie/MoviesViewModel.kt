package com.mahesaiqbal.moviecatalogue.ui.movie

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mahesaiqbal.moviecatalogue.data.source.MovieRepository
import com.mahesaiqbal.moviecatalogue.data.source.remote.RemoteRepository
import com.mahesaiqbal.moviecatalogue.data.source.remote.response.movies.ResultMovie

class MoviesViewModel(var movieRepository: MovieRepository) : ViewModel() {

    private val remoteRepository = RemoteRepository()

    var movie = MutableLiveData<MutableList<ResultMovie>>()

    fun getMovies() {
        movie = movieRepository.getAllMovies()
    }

    override fun onCleared() {
        super.onCleared()
        remoteRepository.onDestroy()
    }
}