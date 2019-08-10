package com.mahesaiqbal.moviecatalogue.network

import com.mahesaiqbal.moviecatalogue.data.source.remote.response.movies.Movies
import com.mahesaiqbal.moviecatalogue.data.source.remote.response.tvshows.TVShows
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface Service {
    @GET("discover/movie")
    fun getDiscoverMovies(@Query("api_key") apiKey: String): Observable<Movies>

    @GET("discover/tv")
    fun getDiscoverTV(@Query("api_key") apiKey: String): Observable<TVShows>
}