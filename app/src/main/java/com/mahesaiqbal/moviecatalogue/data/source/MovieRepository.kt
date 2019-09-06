package com.mahesaiqbal.moviecatalogue.data.source

import androidx.lifecycle.LiveData
import com.mahesaiqbal.moviecatalogue.data.source.local.LocalRepository
import com.mahesaiqbal.moviecatalogue.data.source.local.entity.movieentity.ResultMovieEntity
import com.mahesaiqbal.moviecatalogue.data.source.local.entity.tvshowentity.ResultTVShowEntity
import com.mahesaiqbal.moviecatalogue.data.source.remote.ApiResponse
import com.mahesaiqbal.moviecatalogue.data.source.remote.RemoteRepository
import com.mahesaiqbal.moviecatalogue.data.source.remote.response.detailmovie.DetailMovie
import com.mahesaiqbal.moviecatalogue.data.source.remote.response.detailtv.DetailTV
import com.mahesaiqbal.moviecatalogue.data.source.remote.response.movies.ResultMovie
import com.mahesaiqbal.moviecatalogue.data.source.remote.response.tvshows.ResultTVShows
import com.mahesaiqbal.moviecatalogue.utils.AppExecutors
import com.mahesaiqbal.moviecatalogue.vo.Resource

class MovieRepository(
    var localRepository: LocalRepository,
    var remoteRepository: RemoteRepository,
    var appExecutors: AppExecutors
) : MovieDataSource {

    companion object {
        @Volatile
        private var INSTANCE: MovieRepository? = null

        fun getInstance(
            localRepository: LocalRepository,
            remoteData: RemoteRepository,
            appExecutor: AppExecutors
        ): MovieRepository? {
            if (INSTANCE == null) {
                synchronized(MovieRepository::class.java) {
                    if (INSTANCE == null) {
                        INSTANCE = MovieRepository(localRepository, remoteData, appExecutor)
                    }
                }
            }
            return INSTANCE
        }
    }

    override fun getAllMovies(): LiveData<Resource<MutableList<ResultMovieEntity>>> {
        return object : NetworkBoundResource<MutableList<ResultMovieEntity>, MutableList<ResultMovie>>(appExecutors) {
            override fun loadFromDB(): LiveData<MutableList<ResultMovieEntity>> {
                return localRepository.getAllMovies()
            }

            override fun shouldFetch(data: MutableList<ResultMovieEntity>): Boolean {
                return data == null || data.size == 0
            }

            override fun createCall(): LiveData<ApiResponse<MutableList<ResultMovie>>>? {
                return remoteRepository.getAllMoviesAsLiveData()
            }

            override fun saveCallResult(data: MutableList<ResultMovie>?) {
                val movieEntities = mutableListOf<ResultMovieEntity>()

                for (i in data!!.indices) {
                    val response: ResultMovie = data[i]
                    val (id, overview, posterPath, releaseDate, title) = response
                    val movie = ResultMovieEntity(id, overview, posterPath, releaseDate, title, false)

                    movieEntities.add(movie)
                }

                localRepository.insertMovies(movieEntities)
            }
        }.asLiveData()
    }

    override fun getAllFavoriteMovies(): LiveData<Resource<MutableList<ResultMovieEntity>>> {
        return object : NetworkBoundResource<MutableList<ResultMovieEntity>, MutableList<ResultMovie>>(appExecutors) {
            override fun loadFromDB(): LiveData<MutableList<ResultMovieEntity>> {
                return localRepository.getFavoritedMovies()
            }

            override fun shouldFetch(data: MutableList<ResultMovieEntity>): Boolean {
                return false
            }

            override fun createCall(): LiveData<ApiResponse<MutableList<ResultMovie>>>? {
                return null
            }

            override fun saveCallResult(data: MutableList<ResultMovie>?) {

            }

        }.asLiveData()
    }

    override fun getAllTVShows(): LiveData<Resource<MutableList<ResultTVShowEntity>>> {
        return object : NetworkBoundResource<MutableList<ResultTVShowEntity>, MutableList<ResultTVShows>>(appExecutors) {
            override fun loadFromDB(): LiveData<MutableList<ResultTVShowEntity>> {
                return localRepository.getAllTVShows()
            }

            override fun shouldFetch(data: MutableList<ResultTVShowEntity>): Boolean {
                return data == null || data.size == 0
            }

            override fun createCall(): LiveData<ApiResponse<MutableList<ResultTVShows>>>? {
                return remoteRepository.getAllTVShowsAsLiveData()
            }

            override fun saveCallResult(data: MutableList<ResultTVShows>?) {
                val tvShowEntities = mutableListOf<ResultTVShowEntity>()

                for (i in data!!.indices) {
                    val response: ResultTVShows = data[i]
                    val (firstAirDate, id, name, overview, posterPath) = response
                    val tvShow = ResultTVShowEntity(id, firstAirDate, name, overview, posterPath, false)

                    tvShowEntities.add(tvShow)
                }

                localRepository.insertTVShows(tvShowEntities)
            }

        }.asLiveData()
    }

    override fun getAllFavoriteTVShows(): LiveData<Resource<MutableList<ResultTVShowEntity>>> {
        return object : NetworkBoundResource<MutableList<ResultTVShowEntity>, MutableList<ResultTVShows>>(appExecutors) {
            override fun loadFromDB(): LiveData<MutableList<ResultTVShowEntity>> {
                return localRepository.getFavoritedTVShows()
            }

            override fun shouldFetch(data: MutableList<ResultTVShowEntity>): Boolean {
                return false
            }

            override fun createCall(): LiveData<ApiResponse<MutableList<ResultTVShows>>>? {
                return null
            }

            override fun saveCallResult(data: MutableList<ResultTVShows>?) {

            }

        }.asLiveData()
    }

    override fun getDetailMovie(movieId: Int): LiveData<Resource<ResultMovieEntity>> {
        return object : NetworkBoundResource<ResultMovieEntity, DetailMovie>(appExecutors) {
            override fun loadFromDB(): LiveData<ResultMovieEntity> {
                return localRepository.getDetailMovie(movieId)
            }

            override fun shouldFetch(data: ResultMovieEntity): Boolean {
                return data == null
            }

            override fun createCall(): LiveData<ApiResponse<DetailMovie>>? {
                return remoteRepository.getDetailMovieAsLiveData(movieId)
            }

            override fun saveCallResult(data: DetailMovie?) {
                val detailMovie: DetailMovie = data!!
                val (id, originalTitle, overview, posterPath, releaseDate, title) = detailMovie
                val detailMovieEntity = ResultMovieEntity(id!!, overview!!, posterPath!!, releaseDate!!, title!!)

//                localRepository.insertDetailMovie(detailMovieEntity)
            }

        }.asLiveData()
    }

    override fun getDetailTV(tvId: Int): LiveData<Resource<ResultTVShowEntity>> {
        return object : NetworkBoundResource<ResultTVShowEntity, DetailTV>(appExecutors) {
            override fun loadFromDB(): LiveData<ResultTVShowEntity> {
                return localRepository.getDetailTVShow(tvId)
            }

            override fun shouldFetch(data: ResultTVShowEntity): Boolean {
                return data == null
            }

            override fun createCall(): LiveData<ApiResponse<DetailTV>>? {
                return remoteRepository.getDetailTVShowAsLiveData(tvId)
            }

            override fun saveCallResult(data: DetailTV?) {
                val detailTV: DetailTV = data!!
                val (firstAirDate, name, overview, posterPath) = detailTV
                val detailTVEntity = ResultTVShowEntity(tvId, firstAirDate!!, name!!, overview!!, posterPath!!)

//                localRepository.insertDetailTVShow(detailTVEntity)
            }

        }.asLiveData()
    }

    override fun setMovieFavorite(movie: ResultMovieEntity, state: Boolean) {
        val runnable = { localRepository.setMovieFavorite(movie, state) }

        appExecutors.diskIO().execute(runnable)
    }

    override fun setTVShowFavorite(tvShow: ResultTVShowEntity, state: Boolean) {
        val runnable = { localRepository.setTVShowFavorite(tvShow, state) }

        appExecutors.diskIO().execute(runnable)
    }
}