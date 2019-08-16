package com.mahesaiqbal.moviecatalogue.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mahesaiqbal.moviecatalogue.data.source.MovieRepository
import com.mahesaiqbal.moviecatalogue.data.source.remote.RemoteRepository
import com.mahesaiqbal.moviecatalogue.data.source.remote.response.detailtv.DetailTV

class DetailTVViewModel(var movieRepository: MovieRepository) : ViewModel() {

    private val remoteRepository = RemoteRepository()

    var detailTV = MutableLiveData<DetailTV>()

    fun getTestTV() {
        detailTV = movieRepository.getDetailTV(60735)
    }

    var tvId: Int? = null

    fun setTVIdValue(id: Int) {
        tvId = id
    }

    fun getTVIdValue(): Int {
        return tvId!!
    }

    fun getTV(): MutableLiveData<DetailTV> = movieRepository.getDetailTV(tvId!!)

    override fun onCleared() {
        super.onCleared()
        remoteRepository.onDestroy()
    }
}