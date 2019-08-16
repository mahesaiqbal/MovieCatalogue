package com.mahesaiqbal.moviecatalogue.data.source.remote

import android.util.Log
import com.mahesaiqbal.moviecatalogue.data.source.remote.response.detailmovie.DetailMovie
import com.mahesaiqbal.moviecatalogue.data.source.remote.response.detailtv.DetailTV
import com.mahesaiqbal.moviecatalogue.data.source.remote.response.movies.Movies
import com.mahesaiqbal.moviecatalogue.data.source.remote.response.movies.ResultMovie
import com.mahesaiqbal.moviecatalogue.data.source.remote.response.tvshows.ResultTVShows
import com.mahesaiqbal.moviecatalogue.data.source.remote.response.tvshows.TVShows
import com.mahesaiqbal.moviecatalogue.network.Client
import com.mahesaiqbal.moviecatalogue.utils.EspressoIdlingResource
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

    fun getAllMovies(callback: LoadMoviesCallback) {
        EspressoIdlingResource.increment()

        apiService.getDiscoverMovies(API_KEY)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<Movies> {
                override fun onSubscribe(d: Disposable) {
                    compositeDisposable.add(d)
                }

                override fun onNext(t: Movies) {
                    callback.onAllMoviesReceived(t.resultMovies)
                    EspressoIdlingResource.decrement()
                }

                override fun onError(e: Throwable) {
                    e.printStackTrace()
                }

                override fun onComplete() {
                }
            })
    }

    fun getAllTVShows(callback: LoadTVShowsCallback) {
        EspressoIdlingResource.increment()

        apiService.getDiscoverTV(API_KEY)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<TVShows> {
                override fun onSubscribe(d: Disposable) {
                    compositeDisposable.add(d)
                }

                override fun onNext(t: TVShows) {
                    callback.onAllTVShowsReceived(t.resultTVShows)
                    EspressoIdlingResource.decrement()
                }

                override fun onError(e: Throwable) {
                    e.printStackTrace()
                }

                override fun onComplete() {
                }
            })
    }

    fun getDetailMovie(movieId: Int, callback: LoadDetailMovie) {
        EspressoIdlingResource.increment()

        apiService.getDetailMovie(movieId, API_KEY)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<DetailMovie> {
                override fun onSubscribe(d: Disposable) {
                    compositeDisposable.add(d)
                }

                override fun onNext(t: DetailMovie) {
                    callback.onDetailMovieReceived(t)
                    EspressoIdlingResource.decrement()
                }

                override fun onError(e: Throwable) {
                    e.printStackTrace()
                }

                override fun onComplete() {
                }
            })
    }

    fun getDetailTV(tvId: Int, callback: LoadDetailTV) {
        EspressoIdlingResource.increment()

        apiService.getDetailTV(tvId, API_KEY)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<DetailTV> {
                override fun onSubscribe(d: Disposable) {
                    compositeDisposable.add(d)
                }

                override fun onNext(t: DetailTV) {
                    callback.onDetailMovieReceived(t)
                    EspressoIdlingResource.decrement()
                }

                override fun onError(e: Throwable) {
                    e.printStackTrace()
                }

                override fun onComplete() {
                }
            })
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

    interface LoadDetailMovie {
        fun onDetailMovieReceived(detailMovie: DetailMovie)
        fun onDataNotAvailable()
    }

    interface LoadDetailTV {
        fun onDetailMovieReceived(detailTV: DetailTV)
        fun onDataNotAvailable()
    }
}