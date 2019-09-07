package com.mahesaiqbal.moviecatalogue.ui.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.mahesaiqbal.moviecatalogue.data.source.MovieRepository
import com.mahesaiqbal.moviecatalogue.data.source.local.entity.movieentity.ResultMovieEntity
import com.mahesaiqbal.moviecatalogue.vo.Resource
import org.junit.Before

import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.ArgumentMatchers
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations

@RunWith(JUnit4::class)
class DetailMovieViewModelTest {

    @Rule
    @JvmField
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    lateinit var movieRepository: MovieRepository

    @Mock
    lateinit var observer: Observer<Resource<ResultMovieEntity>>

    private var viewModel: DetailMovieViewModel? = null

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        viewModel = DetailMovieViewModel(movieRepository)
    }

    @Test
    fun getDetailMovie() {
        val dummyDetailMovie = MutableLiveData<Resource<ResultMovieEntity>>()
        val resultMovie = mock(ResultMovieEntity::class.java)

        dummyDetailMovie.setValue(Resource.success(resultMovie))

        `when`<LiveData<Resource<ResultMovieEntity>>>(movieRepository.getDetailMovie(ArgumentMatchers.anyInt())).thenReturn(
            dummyDetailMovie
        )

        viewModel?.getDetailMovieTest()?.observeForever(observer)

        verify(observer).onChanged(Resource.success(resultMovie))
    }
}