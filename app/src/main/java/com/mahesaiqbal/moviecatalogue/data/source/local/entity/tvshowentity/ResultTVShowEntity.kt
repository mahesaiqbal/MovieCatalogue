package com.mahesaiqbal.moviecatalogue.data.source.local.entity.tvshowentity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "resulttvshowentities")
data class ResultTVShowEntity(
    @PrimaryKey
    @ColumnInfo(name = "id")
    var id: Int,
    @ColumnInfo(name = "firstAirDate")
    var firstAirDate: String,
    @ColumnInfo(name = "name")
    var name: String,
    @ColumnInfo(name = "overview")
    var overview: String,
    @ColumnInfo(name = "posterPath")
    var posterPath: String,
    @ColumnInfo(name = "favorited")
    var favorited: Boolean = false
)