package com.mahesaiqbal.moviecatalogue.data.source.local

import androidx.lifecycle.LiveData
import com.mahesaiqbal.moviecatalogue.data.source.local.entity.movieentity.ResultMovieEntity
import com.mahesaiqbal.moviecatalogue.data.source.local.entity.tvshowentity.ResultTVShowEntity
import com.mahesaiqbal.moviecatalogue.data.source.local.room.MovieDao

class LocalRepository(val mMovieDao: MovieDao) {

    companion object {
        private var INSTANCE: LocalRepository? = null

        fun getInstance(movieDao: MovieDao): LocalRepository {
            if (INSTANCE == null) {
                INSTANCE = LocalRepository(movieDao)
            }
            return INSTANCE!!
        }
    }

    fun getAllMovies(): LiveData<MutableList<ResultMovieEntity>> = mMovieDao.getMovies()

    fun getFavoritedMovies(): LiveData<MutableList<ResultMovieEntity>> = mMovieDao.getFavoritedMovies()

    fun getAllTVShows(): LiveData<MutableList<ResultTVShowEntity>> = mMovieDao.getTVShows()

    fun getFavoritedTVShows(): LiveData<MutableList<ResultTVShowEntity>> = mMovieDao.getFavoritedTVShows()

    fun getDetailMovie(id: Int): LiveData<ResultMovieEntity> = mMovieDao.getDetailMovie(id)

    fun getDetailTVShow(id: Int): LiveData<ResultTVShowEntity> = mMovieDao.getDetailTVShow(id)

    fun insertMovies(movies: MutableList<ResultMovieEntity>) {
        mMovieDao.insertMovies(movies)
    }

    fun insertTVShows(tvShows: MutableList<ResultTVShowEntity>) {
        mMovieDao.insertTVShows(tvShows)
    }

    fun setMovieFavorite(movie: ResultMovieEntity, newState: Boolean) {
        movie.favorited = newState
        mMovieDao.updateMovie(movie)
    }

    fun setTVShowFavorite(tvShow: ResultTVShowEntity, newState: Boolean) {
        tvShow.favorited = newState
        mMovieDao.updateTVShow(tvShow)
    }
}