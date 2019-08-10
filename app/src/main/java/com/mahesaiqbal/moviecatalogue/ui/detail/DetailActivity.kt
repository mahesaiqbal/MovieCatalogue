package com.mahesaiqbal.moviecatalogue.ui.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.mahesaiqbal.moviecatalogue.R
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {

//    lateinit var viewModel: DetailViewModel
//    lateinit var movies: Movies
//    lateinit var tvShows: TVShows
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_detail)
//
//        supportActionBar?.apply {
//            setDisplayHomeAsUpEnabled(true)
//            setTitle("Detail Movie")
//        }
//
//        viewModel = ViewModelProviders.of(this).get(DetailViewModel::class.java)
//
//        val extras: Bundle? = intent.extras
//        if (extras != null) {
//            val fromFragment = extras.getString("from")
//            val titleMovie = extras.getString("title_movie")
//            if (titleMovie != null && fromFragment != null) {
//                if (fromFragment.equals("MoviesFragment")) {
//                    viewModel.setTitleMovieValue(titleMovie)
//                    movies = viewModel.getMovies()
//                    populateMovie(movies)
//                } else {
//                    viewModel.setTitleMovieValue(titleMovie)
//                    tvShows = viewModel.getTVShows()
//                    populateMovie(tvShows)
//                }
//            }
//        }
//    }
//
//    fun populateMovie(movies: Movies) {
//        tv_title.text = movies.title
//        tv_release_date.text = movies.releaseDate
//        tv_overview_value.text = movies.overview
//
//        Glide.with(this)
//            .load(movies.posterPath)
//            .apply(RequestOptions.placeholderOf(R.drawable.ic_movie).error(R.drawable.ic_error))
//            .into(img_poster)
//    }
//
//    fun populateMovie(tvShows: TVShows) {
//        tv_title.text = tvShows.title
//        tv_release_date.text = tvShows.releaseDate
//        tv_overview_value.text = tvShows.overview
//
//        Glide.with(this)
//            .load(tvShows.posterPath)
//            .apply(RequestOptions.placeholderOf(R.drawable.ic_movie).error(R.drawable.ic_error))
//            .into(img_poster)
//    }
}
