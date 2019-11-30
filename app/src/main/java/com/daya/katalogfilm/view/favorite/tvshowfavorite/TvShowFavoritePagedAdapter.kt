package com.daya.katalogfilm.view.favorite.tvshowfavorite

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.daya.katalogfilm.R
import com.daya.katalogfilm.model.favorite.TvShowFavModel
import com.daya.katalogfilm.util.loadWithGLide
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.layout_item_fav.*

class TvShowFavoritePagedAdapter : PagedListAdapter<TvShowFavModel, TvShowFavoritePagedAdapter.TvShowFavViewHolder>(DIFF_CALLBACK){
    private lateinit var clickListener: (TvShowFavModel) -> Unit

    fun setOnItemClickListener(clickListener: (TvShowFavModel) -> Unit) {
        this.clickListener = clickListener
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TvShowFavViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.layout_item_fav, parent, false)
        return TvShowFavViewHolder(view)
    }

    override fun onBindViewHolder(holder: TvShowFavViewHolder, position: Int) {
        val item = getItem(position)

        holder.bind(item)
    }

    companion object{
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<TvShowFavModel>() {
            override fun areItemsTheSame(oldmovie: TvShowFavModel, newmovie: TvShowFavModel)=oldmovie.id == newmovie.id

            override fun areContentsTheSame(oldItem: TvShowFavModel, newItem: TvShowFavModel)=oldItem == newItem
        }
    }

    inner class TvShowFavViewHolder(override val containerView: View) :
        RecyclerView.ViewHolder(containerView), LayoutContainer {
        private val listener = clickListener

        fun bind(item: TvShowFavModel?) {
            item?.let {
                item_title_fav.text = it.title
                item_date_release_fav.text = it.date
                item_rating_fav.text  = it.rating.toString()
                item_img_fav.loadWithGLide(itemView.context, it.poster_path)
                item_desc_fav.text = it.description

                itemView.setOnClickListener {
                    listener(item)
                }
            }
        }
    }
}

