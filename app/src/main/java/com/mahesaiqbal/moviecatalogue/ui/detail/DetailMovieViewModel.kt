package com.mahesaiqbal.moviecatalogue.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.mahesaiqbal.moviecatalogue.data.source.MovieRepository
import com.mahesaiqbal.moviecatalogue.data.source.local.entity.movieentity.ResultMovieEntity
import com.mahesaiqbal.moviecatalogue.data.source.remote.RemoteRepository
import com.mahesaiqbal.moviecatalogue.data.source.remote.response.detailmovie.DetailMovie
import com.mahesaiqbal.moviecatalogue.vo.Resource

class DetailMovieViewModel(var movieRepository: MovieRepository) : ViewModel() {

    private val remoteRepository = RemoteRepository()

    var detailMovie = MutableLiveData<DetailMovie>()

    var movieId: MutableLiveData<Int> = MutableLiveData()

//    fun getTestMovie() {
//        detailMovie = movieRepository.getDetailMovie(384018)
//    }

    fun setMovieIdValue(id: Int) {
        movieId.value = id
    }

    fun getMovieIdValue(): Int = movieId.value!!

//    fun getMovie(): MutableLiveData<DetailMovie> = movieRepository.getDetailMovie(movieId!!)

    var movieDetail: LiveData<Resource<ResultMovieEntity>> = Transformations.switchMap(movieId) {
            movieId -> movieRepository.getDetailMovie(movieId)
    }

    fun setFavorite() {
        val movieWithDetail = movieDetail.value
        if (movieWithDetail != null) {
            val movieEntity: ResultMovieEntity? = movieWithDetail.data
            val newState = !movieEntity!!.favorited

            movieRepository.setMovieFavorite(movieEntity, newState)
        }
    }

    override fun onCleared() {
        super.onCleared()
        remoteRepository.onDestroy()
    }
}