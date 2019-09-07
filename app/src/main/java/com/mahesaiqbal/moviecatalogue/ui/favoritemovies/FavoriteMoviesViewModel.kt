package com.mahesaiqbal.moviecatalogue.ui.favoritemovies

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.mahesaiqbal.moviecatalogue.data.source.MovieRepository
import com.mahesaiqbal.moviecatalogue.data.source.local.entity.movieentity.ResultMovieEntity
import com.mahesaiqbal.moviecatalogue.vo.Resource

class FavoriteMoviesViewModel(var movieRepository: MovieRepository) : ViewModel() {

    fun getAllFavoriteMovies(): LiveData<Resource<PagedList<ResultMovieEntity>>> = movieRepository.getAllFavoriteMovies()

    fun setFavorite(movie: ResultMovieEntity) {
        val newState = !movie.favorited
        movieRepository.setMovieFavorite(movie, newState)
    }
}
