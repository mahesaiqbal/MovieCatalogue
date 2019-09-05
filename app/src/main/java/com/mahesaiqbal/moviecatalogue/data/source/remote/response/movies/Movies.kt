package com.mahesaiqbal.moviecatalogue.data.source.remote.response.movies

import com.google.gson.annotations.SerializedName

data class Movies(
    @SerializedName("results")
    val resultMovies: MutableList<ResultMovie>
)