package com.mahesaiqbal.moviecatalogue.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider.NewInstanceFactory
import com.mahesaiqbal.moviecatalogue.data.source.MovieRepository
import com.mahesaiqbal.moviecatalogue.di.Injection
import com.mahesaiqbal.moviecatalogue.ui.detail.DetailMovieViewModel
import com.mahesaiqbal.moviecatalogue.ui.detail.DetailTVViewModel
import com.mahesaiqbal.moviecatalogue.ui.movie.MoviesViewModel
import com.mahesaiqbal.moviecatalogue.ui.tvshow.TVShowsViewModel

class ViewModelFactory(movieRepository: MovieRepository) : NewInstanceFactory() {

    var mMovieRepository: MovieRepository = movieRepository

    companion object {
        @Volatile
        private var INSTANCE: ViewModelFactory? = null

        fun getInstance(): ViewModelFactory? {
            if (INSTANCE == null) {
                synchronized(ViewModelFactory::class.java) {
                    if (INSTANCE == null) {
                        INSTANCE = ViewModelFactory(Injection.provideRepository())
                    }
                }
            }
            return INSTANCE
        }
    }

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MoviesViewModel::class.java)) {
            return MoviesViewModel(mMovieRepository) as T
        } else if (modelClass.isAssignableFrom(TVShowsViewModel::class.java)) {
            return TVShowsViewModel(mMovieRepository) as T
        } else if (modelClass.isAssignableFrom(DetailMovieViewModel::class.java)) {
            return DetailMovieViewModel(mMovieRepository) as T
        } else if (modelClass.isAssignableFrom(DetailTVViewModel::class.java)) {
            return DetailTVViewModel(mMovieRepository) as T
        }

        throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
    }
}