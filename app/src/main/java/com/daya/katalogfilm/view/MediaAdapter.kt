package com.daya.katalogfilm.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.daya.katalogfilm.R
import com.daya.katalogfilm.util.loadWithGLide
import com.daya.katalogfilm.model.movie.MovieModel
import com.daya.katalogfilm.model.tv_show.TvShowModel
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.layout_item_list.*

class MediaAdapter(val type: String) : RecyclerView.Adapter<MediaAdapter.MediaViewHolder>() {
    companion object{
        const val TYPE_MOVIE = "movie"
        const val TYPE_TV_SHOW = "tv"
    }
    private var movieList = mutableListOf<com.daya.katalogfilm.model.movie.Result>()
    private var tvShowList = mutableListOf<com.daya.katalogfilm.model.tv_show.Result>()

    lateinit var onItemClickedListener: OnItemClickedListener

    fun setOnItemClickedByListener(listener: OnItemClickedListener) {
        this.onItemClickedListener = listener
    }


    fun setList(list: Any) {
        when (list) {
            is MovieModel -> {
                val diffcallback = MediaDiffUtil(movieList,list.results)
                val diffResult = DiffUtil.calculateDiff(diffcallback)
                movieList.clear()
                movieList.addAll(list.results)
                diffResult.dispatchUpdatesTo(this)

            }
            is TvShowModel ->{
                val diffcallback = MediaDiffUtil(tvShowList,list.results)
                val diffResult = DiffUtil.calculateDiff(diffcallback)
                tvShowList.clear()
                tvShowList.addAll(list.results)
                diffResult.dispatchUpdatesTo(this)
            }
            else -> error("list cannot be cast as moviemodel nor tvshowmodel")
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MediaViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.layout_item_list, parent, false)

        return MediaViewHolder(view)
    }

    override fun getItemCount(): Int =
        when (type) {
            TYPE_MOVIE -> movieList.size
            TYPE_TV_SHOW -> tvShowList.size
            else -> 0
        }


    override fun onBindViewHolder(holder: MediaViewHolder, position: Int) {
        when (type) {
            TYPE_MOVIE -> {
                holder.bindMovie(movieList[position])
            }
            TYPE_TV_SHOW -> {
                holder.bindtvShow(tvShowList[position])
            }
        }
    }


    inner class MediaDiffUtil(val newList: Any, val oldList: Any) : DiffUtil.Callback() {
        var oldlistmovie=   mutableListOf<com.daya.katalogfilm.model.movie.Result>()
        var newlistmovie =   mutableListOf<com.daya.katalogfilm.model.movie.Result>()
        var oldlisttvshow =  mutableListOf<com.daya.katalogfilm.model.tv_show.Result>()
        var newlisttvshow =  mutableListOf<com.daya.katalogfilm.model.tv_show.Result>()


        init {
            when (type) {
                TYPE_MOVIE -> {
                    oldlistmovie.addAll(oldList as List<com.daya.katalogfilm.model.movie.Result>)
                    newlistmovie.addAll(newList as List<com.daya.katalogfilm.model.movie.Result>)

                }
                TYPE_TV_SHOW -> {
                    oldlisttvshow.addAll(oldList as List<com.daya.katalogfilm.model.tv_show.Result>)
                    newlisttvshow.addAll(newList as List<com.daya.katalogfilm.model.tv_show.Result>)
                }
                else -> error("incompatible type of list")
            }
        }

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int) =
            when (type ) {
                TYPE_MOVIE ->
                    newlistmovie[newItemPosition].id == oldlistmovie[oldItemPosition].id
                else ->
                    newlisttvshow[newItemPosition].id == oldlisttvshow[oldItemPosition].id
            }

        override fun getOldListSize(): Int = if (type == TYPE_MOVIE) oldlistmovie.size else oldlisttvshow.size

        override fun getNewListSize(): Int = if (type == TYPE_MOVIE)  newlistmovie.size else newlisttvshow.size

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int) =
            when (type) {
                TYPE_MOVIE ->
                    newlistmovie[newItemPosition] == oldlistmovie[oldItemPosition]

                else ->
                    newlisttvshow[newItemPosition] == oldlisttvshow[oldItemPosition]
            }
    }

    inner class MediaViewHolder(override val containerView: View) :
        RecyclerView.ViewHolder(containerView), LayoutContainer {




        fun bindtvShow(resultTvShow: com.daya.katalogfilm.model.tv_show.Result) {
            with(resultTvShow){
                item_img.loadWithGLide(containerView.context,poster_path)
                item_title.text = name
                item_rating.text = vote_average.toString()
                item_date_release.text = first_air_date
            }

            containerView.setOnClickListener {
                onItemClickedListener.itemTvClicked(resultTvShow)
            }
        }

        fun bindMovie(resultMovie: com.daya.katalogfilm.model.movie.Result) {
            with(resultMovie){
                item_img.loadWithGLide(containerView.context,this.poster_path)
                item_title.text = title
                item_rating.text = vote_average.toString()
                item_date_release.text = release_date
            }

            containerView.setOnClickListener {
                onItemClickedListener.itemMovieClicked(resultMovie)
            }
        }
    }

    interface OnItemClickedListener{
        fun itemMovieClicked( resultMovie: com.daya.katalogfilm.model.movie.Result )
        fun itemTvClicked( resultTvShow: com.daya.katalogfilm.model.tv_show.Result)
    }
}