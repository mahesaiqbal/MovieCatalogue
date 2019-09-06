package com.mahesaiqbal.moviecatalogue.data.source.local.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.mahesaiqbal.moviecatalogue.data.source.local.entity.movieentity.ResultMovieEntity
import com.mahesaiqbal.moviecatalogue.data.source.local.entity.tvshowentity.ResultTVShowEntity

@Database(
    entities = arrayOf(ResultMovieEntity::class, ResultTVShowEntity::class),
    version = 1,
    exportSchema = false
)
abstract class MovieDatabase : RoomDatabase() {

    abstract fun movieDao(): MovieDao

    companion object {

        private var INSTANCE: MovieDatabase? = null

        private val sLock = Any()

        fun getInstance(context: Context): MovieDatabase {
            synchronized(sLock) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        MovieDatabase::class.java, "movies.db"
                    )
                        .build()
                }
                return INSTANCE!!
            }
        }
    }

}