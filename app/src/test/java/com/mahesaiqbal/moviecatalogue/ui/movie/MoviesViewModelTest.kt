package com.mahesaiqbal.moviecatalogue.ui.movie

import org.junit.After
import org.junit.Before

import org.junit.Assert.*
import org.junit.Test

class MoviesViewModelTest {

    var viewModel: MoviesViewModel? = null

    @Before
    fun setUp() {
        viewModel = MoviesViewModel()
    }

    @After
    fun tearDown() {
    }

    @Test
    fun getMovies() {
        val moviesList: List<Movies> = viewModel!!.getMovies()
        assertNotNull(moviesList)
        assertEquals(10, moviesList.size)
    }
}