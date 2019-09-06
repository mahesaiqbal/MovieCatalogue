package com.mahesaiqbal.moviecatalogue.data.source.local.entity.movieentity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "resultmovieentities")
data class ResultMovieEntity(
    @PrimaryKey
    @ColumnInfo(name = "id")
    var id: Int,
    @ColumnInfo(name = "overview")
    var overview: String,
    @ColumnInfo(name = "posterPath")
    var posterPath: String,
    @ColumnInfo(name = "releaseDate")
    var releaseDate: String,
    @ColumnInfo(name = "title")
    var title: String,
    @ColumnInfo(name = "favorited")
    var favorited: Boolean = false
)