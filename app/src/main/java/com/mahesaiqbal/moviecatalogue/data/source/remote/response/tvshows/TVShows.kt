package com.mahesaiqbal.moviecatalogue.data.source.remote.response.tvshows


import com.google.gson.annotations.SerializedName

data class TVShows(
    @SerializedName("results")
    val resultTVShows: MutableList<ResultTVShows>
)