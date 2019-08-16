package com.mahesaiqbal.moviecatalogue.ui.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.mahesaiqbal.moviecatalogue.data.source.MovieRepository
import com.mahesaiqbal.moviecatalogue.data.source.remote.response.detailtv.DetailTV
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
    lateinit var observer: Observer<DetailTV>

    private var viewModel: DetailTVViewModel? = null

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        viewModel = DetailTVViewModel(movieRepository)
    }

    @Test
    fun getDetailMovie() {
        var detailTV = DetailTV()

        val expected = MutableLiveData<DetailTV>()
        expected.postValue(detailTV)

        `when`(movieRepository.getDetailTV(ArgumentMatchers.anyInt())).thenReturn(expected)

        viewModel?.getTestTV()
        viewModel?.detailTV?.observeForever(observer)

        Mockito.verify(observer).onChanged(detailTV)

        assertNotNull(viewModel?.getTestTV())
    }
}