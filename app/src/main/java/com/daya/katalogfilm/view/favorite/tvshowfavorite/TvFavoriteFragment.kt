package com.daya.katalogfilm.view.favorite.tvshowfavorite


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import androidx.paging.PagedList
import androidx.recyclerview.widget.LinearLayoutManager
import com.daya.katalogfilm.R
import com.daya.katalogfilm.model.favorite.TvShowFavModel
import com.daya.katalogfilm.util.obtainViewModel
import com.daya.katalogfilm.view.detail.DetailTvShowFragment.Companion.EXTRA_TV
import com.daya.katalogfilm.viewmodel.DetailViewModel
import com.daya.katalogfilm.viewmodel.FavoriteViewModel
import kotlinx.android.synthetic.main.fragment_tv_favorite.*

class TvFavoriteFragment : Fragment() {
    lateinit var detailViewmodel: DetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_tv_favorite, container, false)
        detailViewmodel = activity?.run {
            obtainViewModel(this,DetailViewModel::class.java)
        } ?: throw  Exception("invalid activity")

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val viewmodel = obtainViewModel(activity!!, FavoriteViewModel::class.java)

        viewmodel.getAllTv().observe(viewLifecycleOwner){
            initRecyclerview(it)
        }
    }

    private fun initRecyclerview(list: PagedList<TvShowFavModel>) {
        val tvshowAdapter = TvShowFavoritePagedAdapter()
        tvshowAdapter.submitList(list)

        f__tvshow_fav_recyclerview.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = tvshowAdapter
        }

        tvshowAdapter.setOnItemClickListener {
            val bundle = Bundle()
            bundle.putParcelable(EXTRA_TV,it)

            findNavController().navigate(R.id.action_global_detailTvShowFragment, bundle)
            detailViewmodel.setTvFav(it)
        }
    }
}
