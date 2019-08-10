package com.mahesaiqbal.moviecatalogue.data.source.remote.response.tvshows


import com.google.gson.annotations.SerializedName

data class TVShows(
    @SerializedName("page")
    val page: Int,
    @SerializedName("results")
    val resultTVShows: MutableList<ResultTVShows>,
    @SerializedName("total_pages")
    val totalPages: Int,
    @SerializedName("total_results")
    val totalResults: Int
)