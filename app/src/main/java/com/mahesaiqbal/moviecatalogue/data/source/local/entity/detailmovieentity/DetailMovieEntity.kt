package com.mahesaiqbal.moviecatalogue.data.source.local.entity.detailmovieentity

import androidx.room.*
import com.mahesaiqbal.moviecatalogue.data.source.local.entity.movieentity.ResultMovieEntity

data class DetailMovieEntity(
    @ColumnInfo(name = "idMovieDetail")
    var id: Int,
    @ColumnInfo(name = "originalTitle")
    var originalTitle: String? = null,
    @ColumnInfo(name = "overviewDetail")
    var overview: String? = null,
    @ColumnInfo(name = "posterPathDetail")
    var posterPath: String? = null,
    @ColumnInfo(name = "releaseDateDetail")
    var releaseDate: String? = null,
    @ColumnInfo(name = "titleDetail")
    var title: String? = null
) {
    @Embedded
    var movie: ResultMovieEntity? = null
}