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
import com.mahesaiqbal.moviecatalogue.data.source.local.entity.tvshowentity.ResultTVShowEntity
import com.mahesaiqbal.moviecatalogue.data.source.remote.response.detailtv.DetailTV
import com.mahesaiqbal.moviecatalogue.viewmodel.ViewModelFactory
import com.mahesaiqbal.moviecatalogue.vo.Resource
import com.mahesaiqbal.moviecatalogue.vo.Status.*
import kotlinx.android.synthetic.main.activity_detail_tv.*

class DetailTVActivity : AppCompatActivity() {

    lateinit var viewModel: DetailTVViewModel

    lateinit var menuDetail: Menu

    companion object {
        fun obtainViewModel(activity: AppCompatActivity): DetailTVViewModel {
            val factory = ViewModelFactory.getInstance(activity.application)
            return ViewModelProviders.of(activity, factory).get(DetailTVViewModel::class.java)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_tv)

        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setTitle("Detail TV")
        }

        viewModel = obtainViewModel(this)

        val extras: Bundle? = intent.extras
        if (extras != null) {
            val courseId = extras.getInt("tv_id")
            if (courseId != null) {
                viewModel.setTVIdValue(courseId)
            }
        }

        viewModel.tvDetail.observe(this, getDetailTV)
    }

    private val getDetailTV = Observer<Resource<ResultTVShowEntity>> { tvWithDetail ->
        if (tvWithDetail != null) {
            when (tvWithDetail.status) {
                LOADING -> progress_bar.visibility = View.VISIBLE
                SUCCESS -> if (tvWithDetail.data != null) {
                    progress_bar.visibility = View.GONE

                    populateTV(tvWithDetail.data)
                }
                ERROR -> progress_bar.visibility = View.GONE
            }
        }
    }

    private val tvFavorited = Observer<Resource<ResultTVShowEntity>> { tvFavorited ->
        if (tvFavorited != null) {
            when (tvFavorited.status) {
                LOADING -> progress_bar.visibility = View.VISIBLE
                SUCCESS -> if ( tvFavorited.data != null) {
                    progress_bar.visibility = View.GONE

                    val state = tvFavorited.data.favorited
                    setFavoriteState(state)
                    Toast.makeText(getApplicationContext(), tvFavorited.data.favorited.toString(), Toast.LENGTH_SHORT).show();
                }
                ERROR -> {
                    progress_bar.visibility = View.GONE
                    Toast.makeText(getApplicationContext(), "Terjadi kesalahan", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_detail, menu)
        menuDetail = menu!!

        viewModel.tvDetail.observe(this, tvFavorited)

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item?.itemId == R.id.action_favorite) {
            viewModel.setFavorite()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    fun populateTV(tv: ResultTVShowEntity) {
        tv_title.text = tv.name
        tv_release_date.text = tv.firstAirDate
        tv_overview_value.text = tv.overview

        Glide.with(this)
            .load("https://image.tmdb.org/t/p/w500${tv.posterPath}")
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
