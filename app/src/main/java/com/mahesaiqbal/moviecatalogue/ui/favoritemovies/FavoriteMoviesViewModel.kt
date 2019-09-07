package com.mahesaiqbal.moviecatalogue.ui.favoritemovies

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.mahesaiqbal.moviecatalogue.data.source.MovieRepository
import com.mahesaiqbal.moviecatalogue.data.source.local.entity.movieentity.ResultMovieEntity
import com.mahesaiqbal.moviecatalogue.vo.Resource

class FavoriteMoviesViewModel(var movieRepository: MovieRepository) : ViewModel() {

    var category: MutableLiveData<String> = MutableLiveData()

    var favMovies = Transformations.switchMap<String, Resource<PagedList<ResultMovieEntity>>>(
        category
    ) { movieRepository.getAllFavoriteMovies() }

    fun setCategory(category: String) {
        this.category.postValue(category)
    }

    fun setFavorite(movie: ResultMovieEntity) {
        val newState = !movie.favorited
        movieRepository.setMovieFavorite(movie, newState)
    }
}
