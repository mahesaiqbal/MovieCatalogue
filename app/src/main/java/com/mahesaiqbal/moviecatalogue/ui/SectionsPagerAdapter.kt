package com.mahesaiqbal.moviecatalogue.ui

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.mahesaiqbal.moviecatalogue.ui.movie.MoviesFragment
import com.mahesaiqbal.moviecatalogue.ui.tvshow.TVShowsFragment

class SectionsPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment? {
        val getFragment = when (position) {
            0 -> MoviesFragment()
            1 -> TVShowsFragment()
            else -> null
        }

        return getFragment
    }

    override fun getPageTitle(position: Int): CharSequence? {
        val getTitle = when (position) {
            0 -> "Movies"
            1 -> "TV Shows"
            else -> ""
        }

        return getTitle
    }

    override fun getCount(): Int = 2
}