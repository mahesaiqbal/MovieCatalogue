package com.mahesaiqbal.moviecatalogue.ui.detail

import org.junit.After
import org.junit.Before

import org.junit.Assert.*
import org.junit.Test

class DetailViewModelTest {

    var viewModel: DetailViewModel? = null
    var dummyMovies: Movies? = null

    @Before
    fun setUp() {
        viewModel = DetailViewModel()
        viewModel!!.setTitleMovieValue("The Lion King")
        dummyMovies = Movies(
            "The Lion King",
            "Simba idolises his father, King Mufasa, and takes to heart his own royal destiny. But not everyone in the kingdom celebrates the new cub's arrival. Scar, Mufasa's brother—and former heir to the throne—has plans of his own. The battle for Pride Rock is ravaged with betrayal, tragedy and drama, ultimately resulting in Simba's exile. With help from a curious pair of newfound friends, Simba will have to figure out how to grow up and take back what is rightfully his.",
            "12 Juli 2019",
            "https://image.tmdb.org/t/p/w342/dzBtMocZuJbjLOXvrl4zGYigDzh.jpg"
        )
    }

    @After
    fun tearDown() {
    }

    @Test
    fun getMovie() {
        viewModel!!.titleMovie = dummyMovies!!.title

        val movies: Movies = viewModel!!.getMovies()

        assertNotNull(movies)
        assertEquals(dummyMovies?.title, movies.title)
        assertEquals(dummyMovies?.overview, movies.overview)
        assertEquals(dummyMovies?.releaseDate, movies.releaseDate)
        assertEquals(dummyMovies?.posterPath, movies.posterPath)
    }
}