package com.mahesaiqbal.moviecatalogue.ui.movie

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.mahesaiqbal.moviecatalogue.data.source.MovieRepository
import com.mahesaiqbal.moviecatalogue.data.source.local.entity.movieentity.ResultMovieEntity
import com.mahesaiqbal.moviecatalogue.vo.Resource
import org.junit.*

import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
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
    lateinit var observer: Observer<Resource<PagedList<ResultMovieEntity>>>

    private var viewModel: MoviesViewModel? = null

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        viewModel = MoviesViewModel(movieRepository)
    }

    @Test
    fun getMovies() {
        val dummyMovie = MutableLiveData<Resource<PagedList<ResultMovieEntity>>>()
        val pagedList = mock(PagedList::class.java) as PagedList<ResultMovieEntity>

        dummyMovie.setValue(Resource.success(pagedList))

        `when`<LiveData<Resource<PagedList<ResultMovieEntity>>>>(movieRepository.getAllMovies()).thenReturn(
            dummyMovie
        )

        viewModel?.getAllMovies()?.observeForever(observer)

        verify(observer).onChanged(Resource.success(pagedList))
    }
}