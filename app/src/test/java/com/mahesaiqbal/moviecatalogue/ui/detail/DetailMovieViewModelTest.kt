package com.mahesaiqbal.moviecatalogue.ui.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.mahesaiqbal.moviecatalogue.data.source.MovieRepository
import com.mahesaiqbal.moviecatalogue.data.source.remote.response.detailmovie.DetailMovie
import com.mahesaiqbal.moviecatalogue.data.source.remote.response.movies.ResultMovie
import org.junit.After
import org.junit.Before

import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.ArgumentMatchers
import org.mockito.Mock
import org.mockito.Mockito
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
    lateinit var observer: Observer<DetailMovie>

    private var viewModel: DetailMovieViewModel? = null

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        viewModel = DetailMovieViewModel(movieRepository)
    }

    @Test
    fun getDetailMovie() {
        var detailMovie = DetailMovie()

        val expected = MutableLiveData<DetailMovie>()
        expected.postValue(detailMovie)

        `when`(movieRepository.getDetailMovie(ArgumentMatchers.anyInt())).thenReturn(expected)

        viewModel?.getTestMovie()
        viewModel?.detailMovie?.observeForever(observer)

        verify(observer).onChanged(detailMovie)

        assertNotNull(viewModel?.getTestMovie())
    }
}