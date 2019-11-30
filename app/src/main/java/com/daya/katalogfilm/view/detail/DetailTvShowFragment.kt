package com.daya.katalogfilm.view.detail


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.observe
import com.daya.katalogfilm.R
import com.daya.katalogfilm.model.favorite.TvShowFavModel
import com.daya.katalogfilm.util.loadWithGLide
import com.daya.katalogfilm.util.obtainViewModel
import com.daya.katalogfilm.viewmodel.DetailViewModel
import com.daya.katalogfilm.viewmodel.FavoriteViewModel
import kotlinx.android.synthetic.main.fragment_detail_tv_show.*
import org.jetbrains.anko.support.v4.toast

class DetailTvShowFragment : Fragment() {
    lateinit var detailViewModel: DetailViewModel
    lateinit var favViewModel: FavoriteViewModel

    companion object{
        const val EXTRA_TV = "extra_tv"
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_detail_tv_show, container, false)
        detailViewModel = activity?.run {
            obtainViewModel(this,DetailViewModel::class.java)
        } ?: throw  Exception("invalid activity")

        favViewModel = obtainViewModel(activity!!, FavoriteViewModel::class.java)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        detailViewModel.getTvFav().observe(viewLifecycleOwner){
            loadDataTv(it)
        }
    }

    fun loadDataTv(it : TvShowFavModel) {
        view?.context?.let { context ->
            detail_tv_img_backdrop.loadWithGLide(context,it.backdrop_path)
            detail_tv_img_poster.loadWithGLide(context, it.poster_path)
        }
        detail_tvl_text_judul.text = it.title
        detail_tv_text_rating.text = getString(R.string.per_ten, it.rating)
        detail_tv_text_deskripsi.text = it.description

        setBtnFavorite(
            TvShowFavModel(
                id = it.id,
                title = it.title,
                description = it.description,
                poster_path = it.poster_path,
                backdrop_path = it.backdrop_path,
                date = it.date,
                rating = it.rating
            )
        )
    }

    fun setBtnFavorite(tvshow: TvShowFavModel) {
        favViewModel.isTvFavExist(tvshow.id).observe(viewLifecycleOwner) {
            detail_tv_btn_fav.isChecked = it != null
        }

        detail_tv_btn_fav.setOnCheckStateChangeListener { view, checked ->

            if (checked) {
                favViewModel.insertTv(tvshow)
                toast("added to favorite")

            } else {
                favViewModel.deleteTv(tvshow)
                toast("deleted from favorite")
            }
            !checked
        }
    }
}
