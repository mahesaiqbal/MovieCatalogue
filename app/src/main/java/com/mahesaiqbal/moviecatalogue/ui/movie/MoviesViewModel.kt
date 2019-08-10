package com.mahesaiqbal.moviecatalogue.ui.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.mahesaiqbal.moviecatalogue.data.source.MovieRepository
import com.mahesaiqbal.moviecatalogue.data.source.remote.RemoteRepository
import com.mahesaiqbal.moviecatalogue.data.source.remote.response.movies.ResultMovie
import com.mahesaiqbal.moviecatalogue.utils.DataDummy

class MoviesViewModel(var movieRepository: MovieRepository) : ViewModel() {

    private val remoteRepository = RemoteRepository()

    fun getMovies(): LiveData<MutableList<ResultMovie>> = movieRepository.getAllMovies()

    override fun onCleared() {
        super.onCleared()
        remoteRepository.onDestroy()
    }
}