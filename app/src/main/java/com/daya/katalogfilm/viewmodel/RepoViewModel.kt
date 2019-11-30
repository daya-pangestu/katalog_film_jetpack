package com.daya.katalogfilm.viewmodel

import androidx.lifecycle.*
import com.daya.katalogfilm.repo.Json
import com.daya.katalogfilm.model.movie.MovieModel
import com.daya.katalogfilm.model.tv_show.TvShowModel
import com.daya.katalogfilm.repo.Repository
import com.google.gson.Gson

class RepoViewModel :ViewModel(){

    private val repo by lazy { Repository() }

    fun initMovie(): LiveData<MovieModel> = repo.getMovieLd()


    fun initTvhow(): LiveData<TvShowModel> = repo.getTvLd()
}