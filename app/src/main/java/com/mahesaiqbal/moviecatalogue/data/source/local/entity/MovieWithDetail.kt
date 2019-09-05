package com.mahesaiqbal.moviecatalogue.data.source.local.entity

import androidx.room.Embedded
import androidx.room.Relation
import com.mahesaiqbal.moviecatalogue.data.source.local.entity.detailmovieentity.DetailMovieEntity
import com.mahesaiqbal.moviecatalogue.data.source.local.entity.movieentity.ResultMovieEntity

//data class MovieWithDetail(
//    @Relation(parentColumn = "movieId", entityColumn = "movieDetailId")
//    var detailMovie: DetailMovieEntity? = null
//) {
//    @Embedded
//    var movie: ResultMovieEntity? = null
//}