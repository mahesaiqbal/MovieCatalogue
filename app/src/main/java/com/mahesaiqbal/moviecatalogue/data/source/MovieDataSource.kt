package com.mahesaiqbal.moviecatalogue.data.source

import androidx.lifecycle.LiveData
import com.mahesaiqbal.moviecatalogue.data.source.local.entity.movieentity.ResultMovieEntity
import com.mahesaiqbal.moviecatalogue.data.source.local.entity.tvshowentity.ResultTVShowEntity
import com.mahesaiqbal.moviecatalogue.vo.Resource

interface MovieDataSource {

    fun getAllMovies(): LiveData<Resource<MutableList<ResultMovieEntity>>>

    fun getAllFavoriteMovies(): LiveData<Resource<MutableList<ResultMovieEntity>>>

    fun getAllTVShows(): LiveData<Resource<MutableList<ResultTVShowEntity>>>

    fun getAllFavoriteTVShows(): LiveData<Resource<MutableList<ResultTVShowEntity>>>

    fun getDetailMovie(movieId: Int): LiveData<Resource<ResultMovieEntity>>

    fun getDetailTV(tvId: Int): LiveData<Resource<ResultTVShowEntity>>

    fun setMovieFavorite(movie: ResultMovieEntity, state: Boolean)

    fun setTVShowFavorite(tvShow: ResultTVShowEntity, state: Boolean)
}