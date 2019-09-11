package com.mahesaiqbal.moviecatalogue.ui.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.mahesaiqbal.moviecatalogue.R
import com.mahesaiqbal.moviecatalogue.data.source.local.entity.movieentity.ResultMovieEntity
import com.mahesaiqbal.moviecatalogue.viewmodel.ViewModelFactory
import com.mahesaiqbal.moviecatalogue.vo.Resource
import com.mahesaiqbal.moviecatalogue.vo.Status.*
import kotlinx.android.synthetic.main.activity_detail.*

class DetailMovieActivity : AppCompatActivity() {

    lateinit var viewModel: DetailMovieViewModel

    private lateinit var menuDetail: Menu

    companion object {
        fun obtainViewModel(activity: AppCompatActivity): DetailMovieViewModel {
            val factory = ViewModelFactory.getInstance(activity.application)
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
                viewModel.setMovieIdValue(courseId)
            }
        }

        viewModel.movieDetail.observe(this, getDetailMovie)
    }

    private val getDetailMovie = Observer<Resource<ResultMovieEntity>> { movieWithDetail ->
        if (movieWithDetail != null) {
            when (movieWithDetail.status) {
                LOADING -> progress_bar.visibility = View.VISIBLE
                SUCCESS -> if (movieWithDetail.data != null) {
                    progress_bar.visibility = View.GONE

                    populateMovie(movieWithDetail.data)
                }
                ERROR -> progress_bar.visibility = View.GONE
            }
        }
    }

    private val movieFavorited = Observer<Resource<ResultMovieEntity>> { movieFavorited ->
        if (movieFavorited != null) {
            when (movieFavorited.status) {
                LOADING -> progress_bar.visibility = View.VISIBLE
                SUCCESS -> if ( movieFavorited.data != null) {
                    progress_bar.visibility = View.GONE

                    val state = movieFavorited.data.favorited
                    setFavoriteState(state)
                }
                ERROR -> {
                    progress_bar.visibility = View.GONE
                    Toast.makeText(getApplicationContext(), "Terjadi kesalahan", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_detail, menu)
        menuDetail = menu

        viewModel.movieDetail.observe(this, movieFavorited)

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item?.itemId == R.id.action_favorite) {
            viewModel.setFavorite()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    private fun populateMovie(movie: ResultMovieEntity) {
        tv_title.text = movie.title
        tv_release_date.text = movie.releaseDate
        tv_overview_value.text = movie.overview

        Glide.with(this)
            .load("https://image.tmdb.org/t/p/w500${movie.posterPath}")
            .apply(RequestOptions.placeholderOf(R.drawable.ic_error).error(R.drawable.ic_error))
            .into(img_poster)
    }

    private fun setFavoriteState(state: Boolean) {
        val menuItem = menuDetail.findItem(R.id.action_favorite)
        if (state) {
            menuItem.setIcon(ContextCompat.getDrawable(this, R.drawable.ic_favorite_white))
        } else {
            menuItem.setIcon(ContextCompat.getDrawable(this, R.drawable.ic_favorite_border_white))
        }
    }
}
