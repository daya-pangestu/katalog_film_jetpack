package com.daya.katalogfilm.view.detail


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.observe
import com.daya.katalogfilm.R
import com.daya.katalogfilm.model.favorite.MovieFavModel
import com.daya.katalogfilm.util.loadWithGLide
import com.daya.katalogfilm.util.obtainViewModel
import com.daya.katalogfilm.viewmodel.DetailViewModel
import com.daya.katalogfilm.viewmodel.FavoriteViewModel
import kotlinx.android.synthetic.main.fragment_detail.*
import org.jetbrains.anko.support.v4.toast


class DetailMovieFragment : Fragment() {
    lateinit var detailViewModel: DetailViewModel
    lateinit var favViewModel: FavoriteViewModel


    companion object{
        const val EXTRA_MOVIE = "extra_movie"
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_detail, container, false)

        detailViewModel = activity?.run {
            obtainViewModel(this, DetailViewModel::class.java)
        } ?: throw  Exception("invalid activity")

        favViewModel = obtainViewModel(activity!!, FavoriteViewModel::class.java)

        return view
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        detailViewModel.getMovieFav().observe(viewLifecycleOwner){
            loadDataToView(it)
        }
    }

    fun loadDataToView(it :MovieFavModel ){
        detail_movie_img_backdrop.loadWithGLide(view!!.context, it.backgrop_path)
        detail_movie_img_poster.loadWithGLide(view!!.context, it.poster_path)
        detail_movie_text_judul.text = it.title
        detail_movie_text_rating.text = getString(R.string.per_ten, it.rating)
        detail_movie_text_deskripsi.text = it.description

        setBtnFavorite(MovieFavModel(
            it.id,
            it.title,
            it.description,
            it.poster_path,
            it.backgrop_path,
            it.date,
            it.rating
        ))
    }

    fun setBtnFavorite(movie: MovieFavModel) {
        favViewModel.isMovieFavExist(movie.id).observe(viewLifecycleOwner) {
            detail_moviel_btn_fav.isChecked = it != null
        }

        detail_moviel_btn_fav.setOnCheckStateChangeListener { view, checked ->

            if (checked) {
                favViewModel.insertMovie(movie)
                toast("added to favorite")

            } else {
                favViewModel.deleteMovie(movie)
                toast("deleted from favorite")
            }
            !checked
        }
    }
}
