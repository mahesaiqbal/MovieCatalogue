package com.mahesaiqbal.moviecatalogue.ui.favoritetvshows

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
import com.mahesaiqbal.moviecatalogue.data.source.local.entity.tvshowentity.ResultTVShowEntity
import com.mahesaiqbal.moviecatalogue.ui.detail.DetailTVActivity
import com.mahesaiqbal.moviecatalogue.ui.favoritetvshows.FavoriteTVShowsPagedAdapter.FavoriteTVShowCallback
import com.mahesaiqbal.moviecatalogue.viewmodel.ViewModelFactory
import com.mahesaiqbal.moviecatalogue.vo.Resource
import com.mahesaiqbal.moviecatalogue.vo.Status.*
import kotlinx.android.synthetic.main.fragment_favorite_tvshows.*

class FavoriteTVShowsFragment : Fragment(), FavoriteTVShowCallback {

    lateinit var favTVShowAdapter: FavoriteTVShowsPagedAdapter
    lateinit var viewModel: FavoriteTVShowsViewModel

    companion object {
        fun obtainViewModel(activity: FragmentActivity): FavoriteTVShowsViewModel {
            val factory = ViewModelFactory.getInstance(activity.application)
            return ViewModelProviders.of(activity, factory).get(FavoriteTVShowsViewModel::class.java)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_favorite_tvshows, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        if (activity != null) {
            progress_bar.visibility = View.VISIBLE

            viewModel = obtainViewModel(activity!!)

            favTVShowAdapter = FavoriteTVShowsPagedAdapter(this)

            viewModel.setCategory("Favorite TV Shows")
            viewModel.favTVShows.observe(this, getTVShow)

            rv_fav_tv_shows.apply {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = favTVShowAdapter
                itemTouchHelper.attachToRecyclerView(this)
            }
        }
    }

    private val getTVShow = Observer<Resource<PagedList<ResultTVShowEntity>>> { favTVShow ->
        if (favTVShow != null) {
            when (favTVShow.status) {
                LOADING -> progress_bar.visibility = View.VISIBLE
                SUCCESS -> {
                    progress_bar.visibility = View.GONE

                    favTVShowAdapter.submitList(favTVShow.data!!)
                    favTVShowAdapter.notifyDataSetChanged()
                }
                ERROR -> {
                    progress_bar.visibility = View.GONE
                    Toast.makeText(context, "Terjadi kesalahan", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private val itemTouchHelper = ItemTouchHelper(object : ItemTouchHelper.Callback() {
        override fun getMovementFlags(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder): Int =
            makeMovementFlags(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT)

        override fun onMove(recyclerView: RecyclerView, viewHolder: ViewHolder, target: RecyclerView.ViewHolder): Boolean = true

        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
            if (view != null) {
                val swipedPosition = viewHolder.adapterPosition
                val movieEntity = favTVShowAdapter.getItemById(swipedPosition)

                viewModel.setFavorite(movieEntity)

                val snackbar = Snackbar.make(view!!, R.string.message_undo, Snackbar.LENGTH_LONG)
                snackbar.setAction(R.string.message_ok) { v -> viewModel.setFavorite(movieEntity) }
                snackbar.show()
            }
        }
    })

    override fun onItemClick(tvShow: ResultTVShowEntity) {
        val intent = Intent(activity, DetailTVActivity::class.java)
        intent.putExtra("tv_id", tvShow.id)
        startActivity(intent)
    }
}
