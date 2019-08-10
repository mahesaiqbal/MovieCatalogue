package com.mahesaiqbal.moviecatalogue.data.source.remote

import android.annotation.SuppressLint
import android.util.Log
import com.mahesaiqbal.moviecatalogue.data.source.remote.response.movies.Movies
import com.mahesaiqbal.moviecatalogue.data.source.remote.response.movies.ResultMovie
import com.mahesaiqbal.moviecatalogue.data.source.remote.response.tvshows.ResultTVShows
import com.mahesaiqbal.moviecatalogue.data.source.remote.response.tvshows.TVShows
import com.mahesaiqbal.moviecatalogue.network.ApiObserver
import com.mahesaiqbal.moviecatalogue.network.Client
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class RemoteRepository {

    companion object {
        private var INSTANCE: RemoteRepository? = null

        fun getInstance(): RemoteRepository? {
            if (INSTANCE == null) {
                INSTANCE = RemoteRepository()
            }
            return INSTANCE
        }
    }

    private val apiService = Client.create()
    private val compositeDisposable = CompositeDisposable()

    val API_KEY = "49a79f125a171a70aafeaefdc6f406b8"

    @SuppressLint("CheckResult")
    fun getAllMovies(callback: LoadMoviesCallback) {

        val movieList = mutableListOf<ResultMovie>()

        apiService.getDiscoverMovies(API_KEY)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<Movies> {
                override fun onComplete() {
                    Log.d("onComplete", "complete")
                }

                override fun onSubscribe(d: Disposable) {
                    compositeDisposable.add(d)
                }

                override fun onNext(t: Movies) {
                    movieList.addAll(t.resultMovies)
                }

                override fun onError(e: Throwable) {
                    e.printStackTrace()
                }

            })

        callback.onAllMoviesReceived(movieList)
    }

    @SuppressLint("CheckResult")
    fun getAllTVShows(callback: LoadTVShowsCallback) {

        val tvShowList = mutableListOf<ResultTVShows>()

        apiService.getDiscoverTV(API_KEY)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<TVShows> {
                override fun onComplete() {
                    Log.d("onComplete", "complete")
                }

                override fun onSubscribe(d: Disposable) {
                    compositeDisposable.add(d)
                }

                override fun onNext(t: TVShows) {
                    tvShowList.addAll(t.resultTVShows)
                }

                override fun onError(e: Throwable) {
                    e.printStackTrace()
                }

            })

        callback.onAllTVShowsReceived(tvShowList)
    }

    fun onDestroy() {
        compositeDisposable.clear()
    }

    interface LoadMoviesCallback {
        fun onAllMoviesReceived(movies: MutableList<ResultMovie>)
        fun onDataNotAvailable()
    }

    interface LoadTVShowsCallback {
        fun onAllTVShowsReceived(tvShows: MutableList<ResultTVShows>)
        fun onDataNotAvailable()
    }
}