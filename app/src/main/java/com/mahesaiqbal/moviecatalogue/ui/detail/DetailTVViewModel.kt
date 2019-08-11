package com.mahesaiqbal.moviecatalogue.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.mahesaiqbal.moviecatalogue.data.source.MovieRepository
import com.mahesaiqbal.moviecatalogue.data.source.remote.RemoteRepository
import com.mahesaiqbal.moviecatalogue.data.source.remote.response.detailtv.DetailTV

class DetailTVViewModel(var movieRepository: MovieRepository) : ViewModel() {

    private val remoteRepository = RemoteRepository()

    var tvId: Int? = null

    fun setTVIdValue(id: Int) {
        tvId = id
    }

    fun getTVIdValue(): Int {
        return tvId!!
    }

    fun getTV(): LiveData<DetailTV> = movieRepository.getDetailTV(tvId!!)

    override fun onCleared() {
        super.onCleared()
        remoteRepository.onDestroy()
    }
}