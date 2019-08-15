package com.mahesaiqbal.moviecatalogue.ui.movie

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
import com.mahesaiqbal.moviecatalogue.data.source.remote.response.movies.ResultMovie
import com.mahesaiqbal.moviecatalogue.ui.detail.DetailMovieActivity
import com.mahesaiqbal.moviecatalogue.ui.movie.MoviesAdapter.MoviesFragmentCallback
import com.mahesaiqbal.moviecatalogue.viewmodel.ViewModelFactory
import kotlinx.android.synthetic.main.fragment_movies.*

class MoviesFragment : Fragment(), MoviesFragmentCallback {

    lateinit var moviesAdapter: MoviesAdapter
    lateinit var viewModel: MoviesViewModel

    var movies: MutableList<ResultMovie> = mutableListOf()

    companion object {
        fun obtainViewModel(activity: FragmentActivity): MoviesViewModel {
            val factory = ViewModelFactory.getInstance()
            return ViewModelProviders.of(activity, factory).get(MoviesViewModel::class.java)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movies, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        if (activity != null) {
            progress_bar.visibility = View.VISIBLE

            viewModel = obtainViewModel(activity!!)

            moviesAdapter = MoviesAdapter(activity!!, movies, this)

            viewModel.movie.observe(this, getMovie)
        }
    }

    private val getMovie = Observer<MutableList<ResultMovie>> { resultMovie ->
        if (resultMovie != null) {
            progress_bar.visibility = View.GONE
            moviesAdapter = MoviesAdapter(activity!!, resultMovie, this)

            rv_movies.apply {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = moviesAdapter
            }

            moviesAdapter.notifyDataSetChanged()
        }
    }

    override fun onItemClick(movies: ResultMovie) {
        val intent = Intent(activity, DetailMovieActivity::class.java)
        intent.putExtra("movie_id", movies.id)
        startActivity(intent)
    }
}
