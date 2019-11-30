package com.daya.katalogfilm.view.tvshow


import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.daya.katalogfilm.R
import com.daya.katalogfilm.model.favorite.TvShowFavModel
import com.daya.katalogfilm.util.obtainViewModel
import com.daya.katalogfilm.view.MediaAdapter
import com.daya.katalogfilm.view.MediaAdapter.Companion.TYPE_TV_SHOW
import com.daya.katalogfilm.viewmodel.DetailViewModel
import com.daya.katalogfilm.viewmodel.RepoViewModel
import kotlinx.android.synthetic.main.fragment_movie.*
import kotlinx.android.synthetic.main.fragment_tvs_show.*

/**
 * A simple [Fragment] subclass.
 */
class TvsShowFragment : Fragment() {

    lateinit var detailViewmodel :DetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_tvs_show, container, false)
        detailViewmodel = activity?.run {
            obtainViewModel(this,DetailViewModel::class.java)
            //ViewModelProviders.of(this)[DetailViewModel::class.java]
        } ?: throw  Exception("invalid activity")
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val viewModel by lazy { ViewModelProviders.of(this)[RepoViewModel::class.java]}
        viewModel.initTvhow().observe(viewLifecycleOwner){
            setupRecyclerView(view.context,it)
        }



    }

    private fun setupRecyclerView(context: Context, list :Any) {
        val mediaAdapter = MediaAdapter(TYPE_TV_SHOW)
        mediaAdapter.setList(list)
        ftvShowRv.apply {
            layoutManager = GridLayoutManager(context, 2)
            setHasFixedSize(true)
            adapter = mediaAdapter
        }

        fTvShowProgres.visibility = View.GONE

        mediaAdapter.setOnItemClickedByListener(object : MediaAdapter.OnItemClickedListener {
            override fun itemMovieClicked(resultMovie: com.daya.katalogfilm.model.movie.Result) {}

            override fun itemTvClicked(resultTvShow: com.daya.katalogfilm.model.tv_show.Result) {
                findNavController().navigate(R.id.action_tvsShowFragment_to_detailTvShowFragment)
                detailViewmodel.setTvFav(
                        TvShowFavModel(
                        id = resultTvShow.id,
                        title = resultTvShow.name,
                        rating = resultTvShow.vote_average,
                        backdrop_path= resultTvShow.backdrop_path,
                        poster_path = resultTvShow.poster_path,
                        description = resultTvShow.overview,
                        date = resultTvShow.first_air_date
                    )
                )

            }
        })
    }
}
