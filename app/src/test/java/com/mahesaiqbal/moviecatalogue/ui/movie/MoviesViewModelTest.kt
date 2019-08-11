package com.mahesaiqbal.moviecatalogue.ui.movie

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.mahesaiqbal.moviecatalogue.data.source.MovieRepository
import com.mahesaiqbal.moviecatalogue.data.source.remote.response.movies.ResultMovie
import com.nhaarman.mockitokotlin2.any
import io.reactivex.Observable
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
class MoviesViewModelTest {

    @Rule
    @JvmField
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    lateinit var movieRepository: MovieRepository

    var viewModel: MoviesViewModel? = null

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        viewModel = MoviesViewModel(movieRepository)
    }

    @After
    fun tearDown() {
    }

    @Test
    fun getMovies() {
        `when`(movieRepository.remoteRepository.getAllMovies(any())).thenAnswer {
            return@thenAnswer Observable.just(ArgumentMatchers.anyList<ResultMovie>())
        }

        val observer = mock(Observer::class.java) as Observer<MutableList<ResultMovie>>

        viewModel?.getMovies()?.observeForever(observer)

        verify(movieRepository).remoteRepository.getAllMovies(any())
    }
}