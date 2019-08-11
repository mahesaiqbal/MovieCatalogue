package com.mahesaiqbal.moviecatalogue.network

import com.mahesaiqbal.moviecatalogue.data.source.remote.response.detailmovie.DetailMovie
import com.mahesaiqbal.moviecatalogue.data.source.remote.response.detailtv.DetailTV
import com.mahesaiqbal.moviecatalogue.data.source.remote.response.movies.Movies
import com.mahesaiqbal.moviecatalogue.data.source.remote.response.tvshows.TVShows
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface Service {
    @GET("discover/movie")
    fun getDiscoverMovies(@Query("api_key") apiKey: String): Observable<Movies>

    @GET("discover/tv")
    fun getDiscoverTV(@Query("api_key") apiKey: String): Observable<TVShows>

    @GET("movie/{movie_id}")
    fun getDetailMovie(@Path("movie_id") movieId: Int, @Query("api_key") apiKey: String): Observable<DetailMovie>

    @GET("tv/{tv_id}")
    fun getDetailTV(@Path("tv_id") tvId: Int, @Query("api_key") apiKey: String): Observable<DetailTV>
}