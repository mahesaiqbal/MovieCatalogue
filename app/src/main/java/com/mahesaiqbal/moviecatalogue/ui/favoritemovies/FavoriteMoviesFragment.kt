package com.mahesaiqbal.moviecatalogue.ui.favoritemovies

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
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.google.android.material.snackbar.Snackbar

import com.mahesaiqbal.moviecatalogue.R
import com.mahesaiqbal.moviecatalogue.data.source.local.entity.movieentity.ResultMovieEntity
import com.mahesaiqbal.moviecatalogue.ui.detail.DetailMovieActivity
import com.mahesaiqbal.moviecatalogue.ui.favoritemovies.FavoriteMoviesPagedAdapter.FavoriteMovieCallback
import com.mahesaiqbal.moviecatalogue.viewmodel.ViewModelFactory
import com.mahesaiqbal.moviecatalogue.vo.Resource
import com.mahesaiqbal.moviecatalogue.vo.Status.*
import kotlinx.android.synthetic.main.fragment_favorite_movies.*

class FavoriteMoviesFragment : Fragment(), FavoriteMovieCallback {

    lateinit var favMovieAdapter: FavoriteMoviesPagedAdapter
    lateinit var viewModel: FavoriteMoviesViewModel

    companion object {
        fun obtainViewModel(activity: FragmentActivity): FavoriteMoviesViewModel {
            val factory = ViewModelFactory.getInstance(activity.application)
            return ViewModelProviders.of(activity, factory).get(FavoriteMoviesViewModel::class.java)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_favorite_movies, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        if (activity != null) {
            progress_bar.visibility = View.VISIBLE

            viewModel = obtainViewModel(activity!!)

            favMovieAdapter = FavoriteMoviesPagedAdapter(this)

            viewModel.setCategory("Favorite Movies")
            viewModel.favMovies.observe(this, getMovie)

            rv_fav_movies.apply {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = favMovieAdapter
                itemTouchHelper.attachToRecyclerView(this)
            }
        }
    }

    private val getMovie = Observer<Resource<PagedList<ResultMovieEntity>>> { favMovie ->
        if (favMovie != null) {
            when (favMovie.status) {
                LOADING -> progress_bar.visibility = View.VISIBLE
                SUCCESS -> {
                    progress_bar.visibility = View.GONE

                    favMovieAdapter.submitList(favMovie.data!!)
                    favMovieAdapter.notifyDataSetChanged()
                }
                ERROR -> {
                    progress_bar.visibility = View.GONE
                    Toast.makeText(context, "Terjadi kesalahan", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private val itemTouchHelper = ItemTouchHelper(object : ItemTouchHelper.Callback() {
        override fun getMovementFlags(recyclerView: RecyclerView, viewHolder: ViewHolder): Int =
            makeMovementFlags(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT)

        override fun onMove(recyclerView: RecyclerView, viewHolder: ViewHolder, target: ViewHolder): Boolean = true

        override fun onSwiped(viewHolder: ViewHolder, direction: Int) {
            if (view != null) {
                val swipedPosition = viewHolder.adapterPosition
                val movieEntity = favMovieAdapter.getItemById(swipedPosition)

                viewModel.setFavorite(movieEntity)

                val snackbar = Snackbar.make(view!!, R.string.message_undo, Snackbar.LENGTH_LONG)
                snackbar.setAction(R.string.message_ok) { v -> viewModel.setFavorite(movieEntity) }
                snackbar.show()
            }
        }
    })

    override fun onItemClick(movie: ResultMovieEntity) {
        val intent = Intent(activity, DetailMovieActivity::class.java)
        intent.putExtra("movie_id", movie.id)
        startActivity(intent)
    }
}
