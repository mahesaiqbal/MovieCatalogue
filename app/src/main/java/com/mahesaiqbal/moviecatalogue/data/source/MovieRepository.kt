package com.mahesaiqbal.moviecatalogue.data.source

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.mahesaiqbal.moviecatalogue.data.source.remote.RemoteRepository
import com.mahesaiqbal.moviecatalogue.data.source.remote.response.detailmovie.DetailMovie
import com.mahesaiqbal.moviecatalogue.data.source.remote.response.detailtv.DetailTV
import com.mahesaiqbal.moviecatalogue.data.source.remote.response.movies.ResultMovie
import com.mahesaiqbal.moviecatalogue.data.source.remote.response.tvshows.ResultTVShows

class MovieRepository(var remoteRepository: RemoteRepository) : MovieDataSource {

    companion object {
        @Volatile
        private var INSTANCE: MovieRepository? = null

        fun getInstance(remoteData: RemoteRepository): MovieRepository? {
            if (INSTANCE == null) {
                synchronized(MovieRepository::class.java) {
                    if (INSTANCE == null) {
                        INSTANCE = MovieRepository(remoteData)
                    }
                }
            }
            return INSTANCE
        }
    }

    override fun getAllMovies(): MutableLiveData<MutableList<ResultMovie>> {
        val movieResults: MutableLiveData<MutableList<ResultMovie>> = MutableLiveData()

        remoteRepository.getAllMovies(object : RemoteRepository.LoadMoviesCallback {
            override fun onAllMoviesReceived(movies: MutableList<ResultMovie>) {
                movieResults.postValue(movies)
            }

            override fun onDataNotAvailable() {

            }
        })

        return movieResults
    }

    override fun getAllTVShows(): MutableLiveData<MutableList<ResultTVShows>> {
        val tvShowResults: MutableLiveData<MutableList<ResultTVShows>> = MutableLiveData()

        remoteRepository.getAllTVShows(object : RemoteRepository.LoadTVShowsCallback {
            override fun onAllTVShowsReceived(tvShows: MutableList<ResultTVShows>) {
                tvShowResults.postValue(tvShows)
            }

            override fun onDataNotAvailable() {

            }
        })

        return tvShowResults
    }

    override fun getDetailMovie(movieId: Int): MutableLiveData<DetailMovie> {
        val movieResult: MutableLiveData<DetailMovie> = MutableLiveData()

        remoteRepository.getDetailMovie(movieId, object : RemoteRepository.LoadDetailMovie {
            override fun onDetailMovieReceived(detailMovie: DetailMovie) {
                movieResult.postValue(detailMovie)
            }

            override fun onDataNotAvailable() {

            }

        })

        return movieResult
    }

    override fun getDetailTV(tvId: Int): MutableLiveData<DetailTV> {
        val tvResult: MutableLiveData<DetailTV> = MutableLiveData()

        remoteRepository.getDetailTV(tvId, object : RemoteRepository.LoadDetailTV {
            override fun onDetailMovieReceived(detailTV: DetailTV) {
                tvResult.postValue(detailTV)
            }

            override fun onDataNotAvailable() {

            }

        })

        return tvResult
    }
}