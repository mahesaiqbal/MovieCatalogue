package com.mahesaiqbal.moviecatalogue.data.source.remote.response.detailtv


import com.google.gson.annotations.SerializedName

data class DetailTV(
    @SerializedName("first_air_date")
    val firstAirDate: String? = null,
    @SerializedName("name")
    val name: String? = null,
    @SerializedName("overview")
    val overview: String? = null,
    @SerializedName("poster_path")
    val posterPath: String? = null
)