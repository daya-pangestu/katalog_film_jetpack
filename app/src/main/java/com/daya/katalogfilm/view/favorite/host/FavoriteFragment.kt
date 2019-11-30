package com.daya.katalogfilm.view.favorite.host


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.daya.katalogfilm.R
import kotlinx.android.synthetic.main.fragment_favorite.*


class FavoriteFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_favorite, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val favAdapter = FavoriteViewPagerAdapter(childFragmentManager)
        f_fav_viewpager.adapter = favAdapter
        f_fav_tab.setViewPager(f_fav_viewpager)

    }


}
