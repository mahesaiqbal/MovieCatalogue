package com.mahesaiqbal.moviecatalogue.ui.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.mahesaiqbal.moviecatalogue.R
import com.mahesaiqbal.moviecatalogue.data.source.remote.response.detailmovie.DetailMovie
import com.mahesaiqbal.moviecatalogue.viewmodel.ViewModelFactory
import kotlinx.android.synthetic.main.activity_detail.*

class DetailMovieActivity : AppCompatActivity() {

    lateinit var viewModel: DetailMovieViewModel

    companion object {
        fun obtainViewModel(activity: AppCompatActivity): DetailMovieViewModel {
            val factory = ViewModelFactory.getInstance()
            return ViewModelProviders.of(activity, factory).get(DetailMovieViewModel::class.java)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setTitle("Detail Movie")
        }

        viewModel = obtainViewModel(this)

        val extras: Bundle? = intent.extras
        if (extras != null) {
            val courseId = extras.getInt("movie_id")
            if (courseId != null) {
                progress_bar.visibility = View.VISIBLE
                viewModel.setMovieIdValue(courseId)
            }
        }

        viewModel.getMovie().observe(this, getDetailMovie)
    }

    private val getDetailMovie = Observer<DetailMovie> { detailMovie ->
        if (detailMovie != null) {
            progress_bar.visibility = View.GONE
            populateMovie(detailMovie)
        }
    }

    fun populateMovie(detailMovie: DetailMovie) {
        tv_title.text = detailMovie.title
        tv_release_date.text = detailMovie.releaseDate
        tv_overview_value.text = detailMovie.overview

        Glide.with(this)
            .load("https://image.tmdb.org/t/p/w500${detailMovie.posterPath}")
            .apply(RequestOptions.placeholderOf(R.drawable.ic_error).error(R.drawable.ic_error))
            .into(img_poster)
    }
}
