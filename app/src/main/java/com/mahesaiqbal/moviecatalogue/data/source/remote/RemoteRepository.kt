package com.mahesaiqbal.moviecatalogue.data.source.remote

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
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

    fun getAllMoviesAsLiveData(): LiveData<ApiResponse<MutableList<ResultMovie>>> {
        EspressoIdlingResource.increment()
        val resultMovie: MutableLiveData<ApiResponse<MutableList<ResultMovie>>> = MutableLiveData()

        apiService.getDiscoverMovies(API_KEY)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<Movies> {
                override fun onSubscribe(d: Disposable) {
                    compositeDisposable.add(d)
                }

                override fun onNext(t: Movies) {
                    resultMovie.postValue(ApiResponse.success(t.resultMovies))
                }

                override fun onError(e: Throwable) {
                    e.printStackTrace()
                }

                override fun onComplete() {
                    if (!EspressoIdlingResource.getEspressoIdlingResource().isIdleNow) {
                        EspressoIdlingResource.decrement()
                    }
                }
            })

        return resultMovie
    }

    fun getAllTVShowsAsLiveData(): LiveData<ApiResponse<MutableList<ResultTVShows>>> {
        EspressoIdlingResource.increment()
        val resultTVShow: MutableLiveData<ApiResponse<MutableList<ResultTVShows>>> = MutableLiveData()

        apiService.getDiscoverTV(API_KEY)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<TVShows> {
                override fun onSubscribe(d: Disposable) {
                    compositeDisposable.add(d)
                }

                override fun onNext(t: TVShows) {
                    resultTVShow.postValue(ApiResponse.success(t.resultTVShows))
                }

                override fun onError(e: Throwable) {
                    e.printStackTrace()
                }

                override fun onComplete() {
                    if (!EspressoIdlingResource.getEspressoIdlingResource().isIdleNow) {
                        EspressoIdlingResource.decrement()
                    }
                }
            })

        return resultTVShow
    }

    fun getDetailMovieAsLiveData(movieId: Int): LiveData<ApiResponse<DetailMovie>> {
        EspressoIdlingResource.increment()
        val resultDetailMovie: MutableLiveData<ApiResponse<DetailMovie>> = MutableLiveData()

        apiService.getDetailMovie(movieId, API_KEY)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<DetailMovie> {
                override fun onSubscribe(d: Disposable) {
                    compositeDisposable.add(d)
                }

                override fun onNext(t: DetailMovie) {
                    resultDetailMovie.postValue(ApiResponse.success(t))
                }

                override fun onError(e: Throwable) {
                    e.printStackTrace()
                }

                override fun onComplete() {
                    if (!EspressoIdlingResource.getEspressoIdlingResource().isIdleNow) {
                        EspressoIdlingResource.decrement()
                    }
                }
            })

        return resultDetailMovie
    }

    fun getDetailTVShowAsLiveData(tvId: Int): LiveData<ApiResponse<DetailTV>> {
        EspressoIdlingResource.increment()
        val resultDetailTVShow: MutableLiveData<ApiResponse<DetailTV>> = MutableLiveData()

        apiService.getDetailTV(tvId, API_KEY)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<DetailTV> {
                override fun onSubscribe(d: Disposable) {
                    compositeDisposable.add(d)
                }

                override fun onNext(t: DetailTV) {
                    resultDetailTVShow.postValue(ApiResponse.success(t))
                }

                override fun onError(e: Throwable) {
                    e.printStackTrace()
                }

                override fun onComplete() {
                    if (!EspressoIdlingResource.getEspressoIdlingResource().isIdleNow) {
                        EspressoIdlingResource.decrement()
                    }
                }
            })

        return resultDetailTVShow
    }

    fun onDestroy() {
        compositeDisposable.clear()
    }
}