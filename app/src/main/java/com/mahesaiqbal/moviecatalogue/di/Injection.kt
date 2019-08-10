package com.mahesaiqbal.moviecatalogue.di

import android.app.Application
import com.mahesaiqbal.moviecatalogue.data.source.MovieRepository
import com.mahesaiqbal.moviecatalogue.data.source.remote.RemoteRepository

class Injection {

    companion object {
        fun provideRepository(): MovieRepository {
            val remoteRepository = RemoteRepository.getInstance()

            return MovieRepository.getInstance(remoteRepository!!)!!
        }
    }
}