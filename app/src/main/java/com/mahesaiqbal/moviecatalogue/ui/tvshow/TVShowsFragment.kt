package com.mahesaiqbal.moviecatalogue.ui.tvshow

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.paging.PagedList
import androidx.recyclerview.widget.LinearLayoutManager

import com.mahesaiqbal.moviecatalogue.R
import com.mahesaiqbal.moviecatalogue.data.source.local.entity.tvshowentity.ResultTVShowEntity
import com.mahesaiqbal.moviecatalogue.ui.detail.DetailTVActivity
import com.mahesaiqbal.moviecatalogue.viewmodel.ViewModelFactory
import com.mahesaiqbal.moviecatalogue.ui.tvshow.TVShowsPagedAdapter.TVShowsFragmentCallback
import com.mahesaiqbal.moviecatalogue.vo.Resource
import com.mahesaiqbal.moviecatalogue.vo.Status.*
import kotlinx.android.synthetic.main.fragment_tvshows.*

class TVShowsFragment : Fragment(), TVShowsFragmentCallback {

    lateinit var tvShowsAdapter: TVShowsPagedAdapter
    lateinit var viewModel: TVShowsViewModel

    var tvShows: MutableList<ResultTVShowEntity> = mutableListOf()

    companion object {
        fun obtainViewModel(activity: FragmentActivity): TVShowsViewModel {
            val factory = ViewModelFactory.getInstance(activity.application)
            return ViewModelProviders.of(activity, factory).get(TVShowsViewModel::class.java)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tvshows, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        if (activity != null) {
            progress_bar.visibility = View.VISIBLE

            viewModel = obtainViewModel(activity!!)

            tvShowsAdapter = TVShowsPagedAdapter(this)

            viewModel.setCategory("TV Shows")
            viewModel.tvShows.observe(this, getTVShow)

            rv_tv_shows.apply {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = tvShowsAdapter
            }
        }
    }

    private val getTVShow = Observer<Resource<PagedList<ResultTVShowEntity>>> { resultTVShow ->
        if (resultTVShow != null) {
            when (resultTVShow.status) {
                LOADING -> progress_bar.visibility = View.VISIBLE
                SUCCESS -> {
                    progress_bar.visibility = View.GONE

                    tvShowsAdapter.submitList(resultTVShow.data!!)
                    tvShowsAdapter.notifyDataSetChanged()
                }
                ERROR -> {
                    progress_bar.visibility = View.GONE
                    Toast.makeText(context, "Terjadi kesalahan", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    override fun onItemClick(tvShows: ResultTVShowEntity) {
        val intent = Intent(activity, DetailTVActivity::class.java)
        intent.putExtra("tv_id", tvShows.id)
        startActivity(intent)
    }
}
