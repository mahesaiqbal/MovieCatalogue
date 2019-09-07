package com.mahesaiqbal.moviecatalogue.ui.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.mahesaiqbal.moviecatalogue.data.source.MovieRepository
import com.mahesaiqbal.moviecatalogue.data.source.local.entity.movieentity.ResultMovieEntity
import com.mahesaiqbal.moviecatalogue.data.source.remote.RemoteRepository
import com.mahesaiqbal.moviecatalogue.vo.Resource

class MoviesViewModel(var movieRepository: MovieRepository) : ViewModel() {

    private val remoteRepository = RemoteRepository()

    fun getAllMovies(): LiveData<Resource<PagedList<ResultMovieEntity>>> = movieRepository.getAllMovies()

    override fun onCleared() {
        super.onCleared()
        remoteRepository.onDestroy()
    }
}