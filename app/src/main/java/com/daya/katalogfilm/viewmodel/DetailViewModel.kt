package com.daya.katalogfilm.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.daya.katalogfilm.model.favorite.MovieFavModel
import com.daya.katalogfilm.model.favorite.TvShowFavModel

class DetailViewModel : ViewModel() {
    private val movieLiveData by lazy { MutableLiveData<com.daya.katalogfilm.model.movie.Result>() }
    private val tvshowLiveData by lazy { MutableLiveData<com.daya.katalogfilm.model.tv_show.Result>() }

    private val movieFavLiveData by lazy { MutableLiveData<MovieFavModel>() }
    private val tvFavLiveData by lazy { MutableLiveData<TvShowFavModel>() }


    fun setMoviee(result: com.daya.katalogfilm.model.movie.Result) {
        movieLiveData.value = result
    }

    fun setTv(tvShowModel: com.daya.katalogfilm.model.tv_show.Result) {
        tvshowLiveData.value = tvShowModel
    }

    fun getMoviee() = movieLiveData

    fun getTv() = tvshowLiveData


    /*-------------------------*/

    fun setTvFav(tvShowFavModel: TvShowFavModel) {
        tvFavLiveData.value = tvShowFavModel
    }

    fun setMovieFav(movieFavModel: MovieFavModel) {
        movieFavLiveData.value = movieFavModel
    }

    fun getMovieFav() = movieFavLiveData

    fun getTvFav() = tvFavLiveData


}