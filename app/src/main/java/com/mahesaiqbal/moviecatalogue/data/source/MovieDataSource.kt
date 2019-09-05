package com.mahesaiqbal.moviecatalogue.data.source

import androidx.lifecycle.LiveData
import com.mahesaiqbal.moviecatalogue.data.source.local.entity.detailmovieentity.DetailMovieEntity
import com.mahesaiqbal.moviecatalogue.data.source.local.entity.detailtventity.DetailTVEntity
import com.mahesaiqbal.moviecatalogue.data.source.local.entity.movieentity.ResultMovieEntity
import com.mahesaiqbal.moviecatalogue.data.source.local.entity.tvshowentity.ResultTVShowEntity
import com.mahesaiqbal.moviecatalogue.vo.Resource

interface MovieDataSource {

    fun getAllMovies(): LiveData<Resource<MutableList<ResultMovieEntity>>>

    fun getAllFavoriteMovies(): LiveData<Resource<MutableList<ResultMovieEntity>>>

    fun getAllTVShows(): LiveData<Resource<MutableList<ResultTVShowEntity>>>

    fun getAllFavoriteTVShows(): LiveData<Resource<MutableList<ResultTVShowEntity>>>

    fun getDetailMovie(movieId: Int): LiveData<Resource<DetailMovieEntity>>

    fun getDetailTV(tvId: Int): LiveData<Resource<DetailTVEntity>>

    fun setMovieFavorite(movie: ResultMovieEntity, state: Boolean)

    fun setTVShowFavorite(tvShow: ResultTVShowEntity, detailTV: DetailTVEntity, state: Boolean)
}