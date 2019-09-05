package com.mahesaiqbal.moviecatalogue.di

import android.app.Application
import com.mahesaiqbal.moviecatalogue.data.source.MovieRepository
import com.mahesaiqbal.moviecatalogue.data.source.local.LocalRepository
import com.mahesaiqbal.moviecatalogue.data.source.local.room.MovieDatabase
import com.mahesaiqbal.moviecatalogue.data.source.remote.RemoteRepository
import com.mahesaiqbal.moviecatalogue.utils.AppExecutors

class Injection {

    companion object {
        fun provideRepository(application: Application): MovieRepository {
            val database = MovieDatabase.getInstance(application)

            val localRepository = LocalRepository.getInstance(database.movieDao())
            val remoteRepository = RemoteRepository.getInstance()

            val appExecutors = AppExecutors()

            return MovieRepository.getInstance(localRepository, remoteRepository!!, appExecutors)!!
        }
    }
}