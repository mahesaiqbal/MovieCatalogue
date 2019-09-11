package com.mahesaiqbal.moviecatalogue.ui

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.mahesaiqbal.moviecatalogue.ui.favoritemovies.FavoriteMoviesFragment
import com.mahesaiqbal.moviecatalogue.ui.favoritetvshows.FavoriteTVShowsFragment
import com.mahesaiqbal.moviecatalogue.ui.movie.MoviesFragment
import com.mahesaiqbal.moviecatalogue.ui.tvshow.TVShowsFragment

class SectionsPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment? {
        return when (position) {
            0 -> MoviesFragment()
            1 -> TVShowsFragment()
            2 -> FavoriteMoviesFragment()
            3 -> FavoriteTVShowsFragment()
            else -> null
        }
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when (position) {
            0 -> "Movies"
            1 -> "TV Shows"
            2 -> "Favorite Movies"
            3 -> "Favorite TV Shows"
            else -> ""
        }
    }

    override fun getCount(): Int = 4
}