package com.mahesaiqbal.moviecatalogue.ui.movie

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.mahesaiqbal.moviecatalogue.data.source.MovieRepository
import com.mahesaiqbal.moviecatalogue.data.source.remote.RemoteRepository
import com.mahesaiqbal.moviecatalogue.data.source.remote.response.movies.ResultMovie
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.argumentCaptor
import io.reactivex.Observable
import org.junit.*

import org.junit.Assert.*
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.ArgumentMatchers
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations

@RunWith(JUnit4::class)
class MoviesViewModelTest {

    @Rule
    @JvmField
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    lateinit var movieRepository: MovieRepository

    @Mock
    lateinit var observer: Observer<MutableList<ResultMovie>>

    private var viewModel: MoviesViewModel? = null

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        viewModel = MoviesViewModel(movieRepository)
    }

    @Test
    fun getMovies() {
        val dummy = mutableListOf<ResultMovie>()

        val expected = MutableLiveData<MutableList<ResultMovie>>()
        expected.postValue(dummy)

        `when`(movieRepository.getAllMovies()).thenReturn(expected)

        viewModel?.getMovies()
        viewModel?.movie?.observeForever(observer)

        verify(observer).onChanged(dummy)

        assertNotNull(viewModel?.movie?.value)
        assertEquals(expected.value, viewModel?.movie?.value)
    }
}