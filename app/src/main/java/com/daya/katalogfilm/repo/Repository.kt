package com.daya.katalogfilm.repo

import androidx.lifecycle.MutableLiveData
import com.daya.katalogfilm.model.movie.MovieModel
import com.daya.katalogfilm.model.tv_show.TvShowModel
import com.google.gson.Gson


class Repository {
    private val gson by lazy{ Gson() }
    private val movieLiveData by lazy {
        MutableLiveData<MovieModel>()
    }
    private val tvLiveData by lazy {
        MutableLiveData<TvShowModel>()
    }

    init {
        parseMoviFromJson()
        parseTvFromJson()
    }

    private fun parseMoviFromJson() {
        val data = gson.fromJson<MovieModel>(Json.movieJson, MovieModel::class.java)
        movieLiveData.value = data
    }

    private fun parseTvFromJson() {
        val data = gson.fromJson<TvShowModel>(Json.tvshowJson, TvShowModel::class.java)
        tvLiveData.value = data
    }

    fun getMovieLd() = movieLiveData

    fun getTvLd() = tvLiveData

}