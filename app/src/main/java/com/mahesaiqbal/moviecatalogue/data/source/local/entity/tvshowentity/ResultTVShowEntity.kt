package com.mahesaiqbal.moviecatalogue.data.source.local.entity.tvshowentity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "resulttvshowentities")
data class ResultTVShowEntity(
    @ColumnInfo(name = "firstAirDate")
    val firstAirDate: String,
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: Int,
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "overview")
    val overview: String,
    @ColumnInfo(name = "posterPath")
    val posterPath: String,
    @ColumnInfo(name = "favorited")
    var favorited: Boolean = false
)