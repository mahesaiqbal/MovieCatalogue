package com.mahesaiqbal.moviecatalogue.ui.tvshow

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.mahesaiqbal.moviecatalogue.data.source.MovieRepository
import com.mahesaiqbal.moviecatalogue.data.source.local.entity.tvshowentity.ResultTVShowEntity
import com.mahesaiqbal.moviecatalogue.vo.Resource
import org.junit.Before

import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.Mockito
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
    lateinit var observer: Observer<Resource<PagedList<ResultTVShowEntity>>>

    private var viewModel: TVShowsViewModel? = null

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        viewModel = TVShowsViewModel(movieRepository)
    }

    @Test
    fun getTVShows() {
        val dummyTV = MutableLiveData<Resource<PagedList<ResultTVShowEntity>>>()
        val pagedList = Mockito.mock(PagedList::class.java) as PagedList<ResultTVShowEntity>

        dummyTV.setValue(Resource.success(pagedList))

        `when`<LiveData<Resource<PagedList<ResultTVShowEntity>>>>(movieRepository.getAllTVShows()).thenReturn(
            dummyTV
        )

        viewModel?.getAllTVShows()?.observeForever(observer)

        verify(observer).onChanged(Resource.success(pagedList))
    }
}