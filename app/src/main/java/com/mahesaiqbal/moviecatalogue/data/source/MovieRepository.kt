package com.mahesaiqbal.moviecatalogue.data.source

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.mahesaiqbal.moviecatalogue.data.source.remote.RemoteRepository
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

    override fun getAllMovies(): LiveData<MutableList<ResultMovie>> {
        val movieResults: MutableLiveData<MutableList<ResultMovie>> = MutableLiveData()

        remoteRepository.getAllMovies(object : RemoteRepository.LoadMoviesCallback {
            override fun onAllMoviesReceived(movies: MutableList<ResultMovie>) {
                movieResults.postValue(movies)
                Log.d("moviesSize", movies.size.toString())
            }

            override fun onDataNotAvailable() {

            }
        })

        return movieResults
    }

    override fun getAllTVShows(): LiveData<MutableList<ResultTVShows>> {
        val tvShowResults: MutableLiveData<MutableList<ResultTVShows>> = MutableLiveData()

        remoteRepository.getAllTVShows(object : RemoteRepository.LoadTVShowsCallback {
            override fun onAllTVShowsReceived(tvShows: MutableList<ResultTVShows>) {
                tvShowResults.postValue(tvShows)
                Log.d("tvShowsSize", tvShows.size.toString())
            }

            override fun onDataNotAvailable() {

            }
        })

        return tvShowResults
    }
}