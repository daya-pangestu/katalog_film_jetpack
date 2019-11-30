package com.daya.katalogfilm.view.movie


import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.daya.katalogfilm.R
import com.daya.katalogfilm.model.favorite.MovieFavModel
import com.daya.katalogfilm.model.movie.MovieModel
import com.daya.katalogfilm.model.movie.Result
import com.daya.katalogfilm.util.obtainViewModel
import com.daya.katalogfilm.view.MediaAdapter
import com.daya.katalogfilm.view.MediaAdapter.Companion.TYPE_MOVIE
import com.daya.katalogfilm.viewmodel.DetailViewModel
import com.daya.katalogfilm.viewmodel.RepoViewModel
import kotlinx.android.synthetic.main.fragment_movie.*
import org.jetbrains.anko.AnkoLogger

class MovieFragment : Fragment() ,AnkoLogger{

    lateinit var detailViewmodel: DetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_movie, container, false)

        detailViewmodel = activity?.run {
            obtainViewModel(this,DetailViewModel::class.java)
        } ?: throw  Exception("invalid activity")
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val viewModel by lazy {ViewModelProviders.of(this)[RepoViewModel::class.java]}

        viewModel.initMovie().observe(viewLifecycleOwner){
            setupRecyclerView(view.context,it)
        }
    }

    private fun setupRecyclerView(context: Context, list : MovieModel) {
        val mediaAdapter= MediaAdapter(TYPE_MOVIE)
        mediaAdapter.setList(list)
        fMovieRv.apply {
            layoutManager = GridLayoutManager(context,2)
            setHasFixedSize(true)
            adapter = mediaAdapter
        }
        fMovieProgres.visibility = View.GONE

        mediaAdapter.setOnItemClickedByListener(object :MediaAdapter.OnItemClickedListener{
            override fun itemMovieClicked(resultMovie: Result) {

                findNavController().navigate(R.id.action_movieFragment_to_detailFragment)
                detailViewmodel.setMovieFav(
                    MovieFavModel(
                        id = resultMovie.id,
                        title = resultMovie.title,
                        description = resultMovie.overview,
                        poster_path = resultMovie.poster_path,
                        backgrop_path = resultMovie.backdrop_path,
                        date = resultMovie.release_date,
                        rating = resultMovie.vote_average
                    )
                )
            }

            override fun itemTvClicked(resultTvShow: com.daya.katalogfilm.model.tv_show.Result) {

            }

        })
    }

}
