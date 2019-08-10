package com.mahesaiqbal.moviecatalogue.data.source

import androidx.lifecycle.LiveData
import com.mahesaiqbal.moviecatalogue.data.source.remote.response.movies.ResultMovie
import com.mahesaiqbal.moviecatalogue.data.source.remote.response.tvshows.ResultTVShows

interface MovieDataSource {

    fun getAllMovies(): LiveData<MutableList<ResultMovie>>

    fun getAllTVShows(): LiveData<MutableList<ResultTVShows>>
}