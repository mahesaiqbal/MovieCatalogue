package com.mahesaiqbal.moviecatalogue.ui.movie

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.paging.PagedList
import androidx.recyclerview.widget.LinearLayoutManager
import com.mahesaiqbal.moviecatalogue.R
import com.mahesaiqbal.moviecatalogue.data.source.local.entity.movieentity.ResultMovieEntity
import com.mahesaiqbal.moviecatalogue.ui.detail.DetailMovieActivity
import com.mahesaiqbal.moviecatalogue.ui.movie.MoviesPagedAdapter.MoviesFragmentCallback
import com.mahesaiqbal.moviecatalogue.viewmodel.ViewModelFactory
import com.mahesaiqbal.moviecatalogue.vo.Resource
import com.mahesaiqbal.moviecatalogue.vo.Status.*
import kotlinx.android.synthetic.main.fragment_movies.*

class MoviesFragment : Fragment(), MoviesFragmentCallback {

    lateinit var moviesAdapter: MoviesPagedAdapter
    lateinit var viewModel: MoviesViewModel

    companion object {
        fun obtainViewModel(activity: FragmentActivity): MoviesViewModel {
            val factory = ViewModelFactory.getInstance(activity.application)
            return ViewModelProviders.of(activity, factory).get(MoviesViewModel::class.java)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_movies, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        if (activity != null) {
            progress_bar.visibility = View.VISIBLE

            viewModel = obtainViewModel(activity!!)

            moviesAdapter = MoviesPagedAdapter(this)

            viewModel.getAllMovies().observe(this, getMovie)

            rv_movies.apply {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = moviesAdapter
            }
        }
    }

    private val getMovie = Observer<Resource<PagedList<ResultMovieEntity>>> { resultMovie ->
        if (resultMovie != null) {
            when (resultMovie.status) {
                LOADING -> progress_bar.visibility = View.VISIBLE
                SUCCESS -> {
                    progress_bar.visibility = View.GONE

                    moviesAdapter.submitList(resultMovie.data!!)
                    moviesAdapter.notifyDataSetChanged()
                }
                ERROR -> {
                    progress_bar.visibility = View.GONE
                    Toast.makeText(context, "Terjadi kesalahan", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    override fun onItemClick(movies: ResultMovieEntity) {
        val intent = Intent(activity, DetailMovieActivity::class.java)
        intent.putExtra("movie_id", movies.id)
        startActivity(intent)
    }
}
