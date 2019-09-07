package com.mahesaiqbal.moviecatalogue.ui.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.mahesaiqbal.moviecatalogue.data.source.MovieRepository
import com.mahesaiqbal.moviecatalogue.data.source.local.entity.tvshowentity.ResultTVShowEntity
import com.mahesaiqbal.moviecatalogue.vo.Resource
import org.junit.Before

import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.ArgumentMatchers
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

@RunWith(JUnit4::class)
class DetailTVViewModelTest {

    @Rule
    @JvmField
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    lateinit var movieRepository: MovieRepository

    @Mock
    lateinit var observer: Observer<Resource<ResultTVShowEntity>>

    private var viewModel: DetailTVViewModel? = null

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        viewModel = DetailTVViewModel(movieRepository)
    }

    @Test
    fun getDetailMovie() {
        val dummyDetailMovie = MutableLiveData<Resource<ResultTVShowEntity>>()
        val resultMovie = mock(ResultTVShowEntity::class.java)

        dummyDetailMovie.setValue(Resource.success(resultMovie))

        `when`<LiveData<Resource<ResultTVShowEntity>>>(movieRepository.getDetailTV(ArgumentMatchers.anyInt())).thenReturn(
            dummyDetailMovie
        )

        viewModel?.getDetailTVShowTest()?.observeForever(observer)

        verify(observer).onChanged(Resource.success(resultMovie))
    }
}