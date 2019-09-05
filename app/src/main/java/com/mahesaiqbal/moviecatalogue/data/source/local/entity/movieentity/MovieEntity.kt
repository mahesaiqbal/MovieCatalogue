package com.mahesaiqbal.moviecatalogue.data.source.local.entity.movieentity

import androidx.room.ColumnInfo
import androidx.room.Entity

//@Entity(tableName = "movieentities")
data class MovieEntity(
    @ColumnInfo(name = "resultMovies")
    val resultMovies: MutableList<ResultMovieEntity>
)