package com.mahesaiqbal.moviecatalogue.data.source.local.entity.movieentity

import androidx.room.ColumnInfo

data class MovieEntity(
    @ColumnInfo(name = "resultMovies")
    val resultMovies: MutableList<ResultMovieEntity>
)