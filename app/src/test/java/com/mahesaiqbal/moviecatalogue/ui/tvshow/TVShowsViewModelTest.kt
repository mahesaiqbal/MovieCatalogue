package com.mahesaiqbal.moviecatalogue.ui.tvshow

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.mahesaiqbal.moviecatalogue.data.source.MovieRepository
import com.mahesaiqbal.moviecatalogue.data.source.remote.response.movies.ResultMovie
import com.mahesaiqbal.moviecatalogue.data.source.remote.response.tvshows.ResultTVShows
import org.junit.After
import org.junit.Before

import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations

@RunWith(JUnit4::class)
class TVShowsViewModelTest {

    @Rule
    @JvmField
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    lateinit var movieRepository: MovieRepository

    @Mock
    lateinit var observer: Observer<MutableList<ResultTVShows>>

    private var viewModel: TVShowsViewModel? = null

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        viewModel = TVShowsViewModel(movieRepository)
    }

    @Test
    fun getTVShows() {
        val dummy = mutableListOf<ResultTVShows>()

        val expected = MutableLiveData<MutableList<ResultTVShows>>()
        expected.postValue(dummy)

        `when`(movieRepository.getAllTVShows()).thenReturn(expected)

        viewModel?.getTVShows()
        viewModel?.tvShow?.observeForever(observer)

        verify(observer).onChanged(dummy)

        assertNotNull(viewModel?.tvShow?.value)
        assertEquals(expected.value, viewModel?.tvShow?.value)
    }
}