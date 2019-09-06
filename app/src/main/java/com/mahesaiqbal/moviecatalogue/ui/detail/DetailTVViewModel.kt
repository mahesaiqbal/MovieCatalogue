package com.mahesaiqbal.moviecatalogue.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.mahesaiqbal.moviecatalogue.data.source.MovieRepository
import com.mahesaiqbal.moviecatalogue.data.source.local.entity.tvshowentity.ResultTVShowEntity
import com.mahesaiqbal.moviecatalogue.data.source.remote.RemoteRepository
import com.mahesaiqbal.moviecatalogue.data.source.remote.response.detailtv.DetailTV
import com.mahesaiqbal.moviecatalogue.vo.Resource

class DetailTVViewModel(var movieRepository: MovieRepository) : ViewModel() {

    private val remoteRepository = RemoteRepository()

    var detailTV = MutableLiveData<DetailTV>()

    var tvId: MutableLiveData<Int> = MutableLiveData()

//    fun getTestTV() {
//        detailTV = movieRepository.getDetailTV(60735)
//    }

    fun setTVIdValue(id: Int) {
        tvId.value = id
    }

    fun getTVIdValue(): Int = tvId.value!!

//    fun getTV(): MutableLiveData<DetailTV> = movieRepository.getDetailTV(tvId!!)

    var tvDetail: LiveData<Resource<ResultTVShowEntity>> = Transformations.switchMap(tvId) {
            tvId -> movieRepository.getDetailTV(tvId)
    }

    fun setFavorite() {
        val tvWithDetail = tvDetail.value
        if (tvWithDetail != null) {
            val tvEntity: ResultTVShowEntity? = tvWithDetail.data
            val newState = !tvEntity!!.favorited

            movieRepository.setTVShowFavorite(tvEntity, newState)
        }
    }

    override fun onCleared() {
        super.onCleared()
        remoteRepository.onDestroy()
    }
}