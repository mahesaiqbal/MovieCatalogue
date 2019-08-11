package com.mahesaiqbal.moviecatalogue.data.source.remote.response.detailmovie


import com.google.gson.annotations.SerializedName

data class SpokenLanguage(
    @SerializedName("iso_639_1")
    val iso6391: String,
    @SerializedName("name")
    val name: String
)