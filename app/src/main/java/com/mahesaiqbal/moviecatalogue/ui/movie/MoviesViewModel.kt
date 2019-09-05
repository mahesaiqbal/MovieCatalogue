package com.mahesaiqbal.moviecatalogue.ui.movie

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.mahesaiqbal.moviecatalogue.data.source.MovieRepository
import com.mahesaiqbal.moviecatalogue.data.source.local.entity.movieentity.ResultMovieEntity
import com.mahesaiqbal.moviecatalogue.data.source.remote.RemoteRepository
import com.mahesaiqbal.moviecatalogue.data.source.remote.response.movies.ResultMovie
import com.mahesaiqbal.moviecatalogue.vo.Resource

class MoviesViewModel(var movieRepository: MovieRepository) : ViewModel() {

    private val remoteRepository = RemoteRepository()

    var movie = MutableLiveData<MutableList<ResultMovie>>()

    var category: MutableLiveData<String> = MutableLiveData()

//    fun getAllMovies(): MutableLiveData<MutableList<ResultMovie>> = movieRepository.getAllMovies()

    var movies = Transformations.switchMap<String, Resource<MutableList<ResultMovieEntity>>>(
        category
    ) { movieRepository.getAllMovies() }

//    fun getMovies() {
//        var movie = Transformations.switchMap<String, Resource<MutableList<ResultMovieEntity>>>(
//            login
//        ) { movieRepository.getAllMovies() }
//    }

    fun setCategory(category: String) {
        this.category.postValue(category)
    }

    override fun onCleared() {
        super.onCleared()
        remoteRepository.onDestroy()
    }
}