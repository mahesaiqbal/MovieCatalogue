package com.mahesaiqbal.moviecatalogue.data.source.local.room

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.*
import com.mahesaiqbal.moviecatalogue.data.source.local.entity.movieentity.ResultMovieEntity
import com.mahesaiqbal.moviecatalogue.data.source.local.entity.tvshowentity.ResultTVShowEntity

@Dao
interface MovieDao {

    @Query("SELECT * FROM resultmovieentities")
    fun getMovies(): DataSource.Factory<Int, ResultMovieEntity>

    @Query("SELECT * FROM resultmovieentities WHERE favorited = 1")
    fun getFavoritedMovies(): DataSource.Factory<Int, ResultMovieEntity>

    @Query("SELECT * FROM resulttvshowentities")
    fun getTVShows(): DataSource.Factory<Int, ResultTVShowEntity>

    @Query("SELECT * FROM resulttvshowentities WHERE favorited = 1")
    fun getFavoritedTVShows(): DataSource.Factory<Int, ResultTVShowEntity>

    @Query("SELECT * FROM resultmovieentities WHERE id = :id")
    fun getDetailMovie(id: Int): LiveData<ResultMovieEntity>

    @Query("SELECT * FROM resulttvshowentities WHERE id = :id")
    fun getDetailTVShow(id: Int): LiveData<ResultTVShowEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovies(movies: MutableList<ResultMovieEntity>): LongArray

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTVShows(tvShows: MutableList<ResultTVShowEntity>): LongArray

    @Update(onConflict = OnConflictStrategy.FAIL)
    fun updateMovie(movie: ResultMovieEntity)

    @Update(onConflict = OnConflictStrategy.FAIL)
    fun updateTVShow(tvShow: ResultTVShowEntity)
}