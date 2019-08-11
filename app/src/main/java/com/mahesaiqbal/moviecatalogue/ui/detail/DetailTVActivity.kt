package com.mahesaiqbal.moviecatalogue.ui.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.mahesaiqbal.moviecatalogue.R
import com.mahesaiqbal.moviecatalogue.data.source.remote.response.detailtv.DetailTV
import com.mahesaiqbal.moviecatalogue.viewmodel.ViewModelFactory
import kotlinx.android.synthetic.main.activity_detail_tv.*

class DetailTVActivity : AppCompatActivity() {

    lateinit var viewModel: DetailTVViewModel

    companion object {
        fun obtainViewModel(activity: AppCompatActivity): DetailTVViewModel {
            val factory = ViewModelFactory.getInstance()
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
                progress_bar.visibility = View.VISIBLE
                viewModel.setTVIdValue(courseId)
            }
        }

        viewModel.getTV().observe(this, getDetailTV)
    }

    private val getDetailTV = Observer<DetailTV> { detailTV ->
        if (detailTV != null) {
            progress_bar.visibility = View.GONE
            populateTV(detailTV)
        }
    }

    fun populateTV(detailTV: DetailTV) {
        tv_title.text = detailTV.name
        tv_release_date.text = detailTV.firstAirDate
        tv_overview_value.text = detailTV.overview

        Glide.with(this)
            .load("https://image.tmdb.org/t/p/w500${detailTV.posterPath}")
            .apply(RequestOptions.placeholderOf(R.drawable.ic_error).error(R.drawable.ic_error))
            .into(img_poster)
    }
}
