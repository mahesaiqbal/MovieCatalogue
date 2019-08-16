package com.mahesaiqbal.moviecatalogue.ui.tvshow

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager

import com.mahesaiqbal.moviecatalogue.R
import com.mahesaiqbal.moviecatalogue.data.source.remote.response.tvshows.ResultTVShows
import com.mahesaiqbal.moviecatalogue.ui.detail.DetailTVActivity
import com.mahesaiqbal.moviecatalogue.viewmodel.ViewModelFactory
import kotlinx.android.synthetic.main.fragment_tvshows.*

class TVShowsFragment : Fragment(), TVShowsAdapter.TVShowsFragmentCallback {

    lateinit var tvShowsAdapter: TVShowsAdapter
    lateinit var viewModel: TVShowsViewModel

    var tvShows: MutableList<ResultTVShows> = mutableListOf()

    companion object {
        fun obtainViewModel(activity: FragmentActivity): TVShowsViewModel {
            val factory = ViewModelFactory.getInstance()
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

            tvShowsAdapter = TVShowsAdapter(activity!!, tvShows, this)

            viewModel.getAllTVShows().observe(this, getTVShow)
        }
    }

    private val getTVShow = Observer<MutableList<ResultTVShows>> { resultTVShow ->
        if (resultTVShow != null) {
            progress_bar.visibility = View.GONE
            tvShowsAdapter = TVShowsAdapter(activity!!, resultTVShow, this)

            rv_tv_shows.apply {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = tvShowsAdapter
            }

            tvShowsAdapter.notifyDataSetChanged()
        }
    }

    override fun onItemClick(tvShows: ResultTVShows) {
        val intent = Intent(activity, DetailTVActivity::class.java)
        intent.putExtra("tv_id", tvShows.id)
        startActivity(intent)
    }
}
