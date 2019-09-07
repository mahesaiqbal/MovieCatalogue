package com.mahesaiqbal.moviecatalogue.data.source.local.entity.tvshowentity

import androidx.room.ColumnInfo
import androidx.room.Entity

data class TVShowEntity(
    @ColumnInfo(name = "resultTVShows")
    val resultTVShows: MutableList<ResultTVShowEntity>
)