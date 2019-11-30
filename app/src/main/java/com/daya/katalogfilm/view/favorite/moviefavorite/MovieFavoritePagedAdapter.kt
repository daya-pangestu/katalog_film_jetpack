package com.daya.katalogfilm.view.favorite.moviefavorite

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.daya.katalogfilm.R
import com.daya.katalogfilm.model.favorite.MovieFavModel
import com.daya.katalogfilm.util.loadWithGLide
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.layout_item_fav.*

class MovieFavoritePagedAdapter : PagedListAdapter<MovieFavModel, MovieFavoritePagedAdapter.MovieFavViewHolder>(DIFF_CALLBACK){
     private lateinit var clickListener : (MovieFavModel) -> Unit

    fun setOnItemClickListener(listener: (MovieFavModel) -> Unit) {
        clickListener = listener
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieFavViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.layout_item_fav, parent, false)

        return MovieFavViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieFavViewHolder, position: Int) {
        val itemMovie = getItem(position)

        holder.bind(itemMovie)

    }

    companion object{
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<MovieFavModel>() {
            override fun areItemsTheSame(oldmovie: MovieFavModel, newmovie: MovieFavModel)=oldmovie.id == newmovie.id

            override fun areContentsTheSame(oldItem: MovieFavModel, newItem: MovieFavModel)=oldItem == newItem
        }
    }
    inner class  MovieFavViewHolder(override val containerView: View) :RecyclerView.ViewHolder(containerView),LayoutContainer{
        private val listener = clickListener

        fun bind(itemMovie: MovieFavModel?) {
            itemMovie?.let {
                item_title_fav.text = it.title
                item_date_release_fav.text = it.date
                item_rating_fav.text  = it.rating.toString()
                item_img_fav.loadWithGLide(itemView.context, it.poster_path)
                item_desc_fav.text = it.description

                itemView.setOnClickListener {
                    listener(itemMovie)
                }
            }
        }
    }
}


