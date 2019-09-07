package com.mahesaiqbal.moviecatalogue.ui.favoritetvshows

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
import com.mahesaiqbal.moviecatalogue.data.source.local.entity.tvshowentity.ResultTVShowEntity
import com.mahesaiqbal.moviecatalogue.vo.Resource
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

@RunWith(JUnit4::class)
class FavoriteTVShowsViewModelTest {

    @Rule
    @JvmField
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    lateinit var movieRepository: MovieRepository

    @Mock
    lateinit var observer: Observer<Resource<PagedList<ResultTVShowEntity>>>

    private var viewModel: FavoriteTVShowsViewModel? = null

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        viewModel = FavoriteTVShowsViewModel(movieRepository)
    }

    @Test
    fun getFavTVShow() {
        val dummyFavTVShow = MutableLiveData<Resource<PagedList<ResultTVShowEntity>>>()
        val pagedList = mock(PagedList::class.java) as PagedList<ResultTVShowEntity>

        dummyFavTVShow.setValue(Resource.success(pagedList))

        `when`<LiveData<Resource<PagedList<ResultTVShowEntity>>>>(movieRepository.getAllFavoriteTVShows()).thenReturn(
            dummyFavTVShow
        )

        viewModel?.getAllFavoriteTVShows()?.observeForever(observer)

        verify(observer).onChanged(Resource.success(pagedList))
    }
}