package com.daya.katalogfilm.view.favorite.moviefavorite


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
import com.daya.katalogfilm.model.favorite.MovieFavModel
import com.daya.katalogfilm.util.obtainViewModel
import com.daya.katalogfilm.view.detail.DetailMovieFragment.Companion.EXTRA_MOVIE
import com.daya.katalogfilm.viewmodel.DetailViewModel
import com.daya.katalogfilm.viewmodel.FavoriteViewModel
import kotlinx.android.synthetic.main.fragment_film_favorite.*

class MovieFavoriteFragment : Fragment() {
    lateinit var detailViewmodel: DetailViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view  = inflater.inflate(R.layout.fragment_film_favorite, container, false)
        detailViewmodel = activity?.run {
            obtainViewModel(this,DetailViewModel::class.java)
        } ?: throw  Exception("invalid activity")

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val viewmodel = obtainViewModel(activity!!, FavoriteViewModel::class.java)

        viewmodel.getAllMovie().observe(viewLifecycleOwner){
            initRecyclerview(it)
        }
    }

    private fun initRecyclerview(list: PagedList<MovieFavModel>) {
        val movieFavAdapter = MovieFavoritePagedAdapter()
        movieFavAdapter.submitList(list)

        f__movie_fav_recyclerview.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = movieFavAdapter
        }

        movieFavAdapter.setOnItemClickListener {
            findNavController().navigate(R.id.action_global_detailFragment)
            detailViewmodel.setMovieFav(it)
        }
    }
}
