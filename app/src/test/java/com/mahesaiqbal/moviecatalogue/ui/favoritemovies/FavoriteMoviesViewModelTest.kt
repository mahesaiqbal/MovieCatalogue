package com.mahesaiqbal.moviecatalogue.ui.favoritemovies

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import org.junit.Before

import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.*
import androidx.paging.PagedList
import com.mahesaiqbal.moviecatalogue.data.source.MovieRepository
import com.mahesaiqbal.moviecatalogue.data.source.local.entity.movieentity.ResultMovieEntity
import com.mahesaiqbal.moviecatalogue.vo.Resource
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

@RunWith(JUnit4::class)
class FavoriteMoviesViewModelTest {

    @Rule
    @JvmField
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    lateinit var movieRepository: MovieRepository

    @Mock
    lateinit var observer: Observer<Resource<PagedList<ResultMovieEntity>>>

    private var viewModel: FavoriteMoviesViewModel? = null

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        viewModel = FavoriteMoviesViewModel(movieRepository)
    }

    @Test
    fun getFavMovie() {
        val dummyFavMovie = MutableLiveData<Resource<PagedList<ResultMovieEntity>>>()
        val pagedList = mock(PagedList::class.java) as PagedList<ResultMovieEntity>

        dummyFavMovie.setValue(Resource.success(pagedList))

        `when`<LiveData<Resource<PagedList<ResultMovieEntity>>>>(movieRepository.getAllFavoriteMovies()).thenReturn(
            dummyFavMovie
        )

        viewModel?.getAllFavoriteMovies()?.observeForever(observer)

        verify(observer).onChanged(Resource.success(pagedList))
    }
}