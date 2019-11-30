package com.daya.katalogfilm.view.favorite.host

import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.daya.katalogfilm.view.favorite.moviefavorite.MovieFavoriteFragment
import com.daya.katalogfilm.view.favorite.tvshowfavorite.TvFavoriteFragment

class FavoriteViewPagerAdapter(fragmentManager: FragmentManager) :
    FragmentStatePagerAdapter(fragmentManager) {

    private val listFragment = arrayListOf(MovieFavoriteFragment(),
        TvFavoriteFragment()
    )

    override fun getItem(position: Int) = listFragment[position]

    override fun getCount()= listFragment.size



    override fun getPageTitle(position: Int)=
        when (position) {
            0 -> "MOVIE"
            1 -> "TV SHOW"
            else -> super.getPageTitle(position)
    }


}