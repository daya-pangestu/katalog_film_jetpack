package com.daya.katalogfilm.repo

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.daya.katalogfilm.model.movie.MovieModel
import com.daya.katalogfilm.model.tv_show.TvShowModel
import com.daya.katalogfilm.viewmodel.RepoViewModel
import com.google.gson.Gson
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class RepoViewModelTest {
    @get:Rule
    var rule = InstantTaskExecutorRule()

    private lateinit var repo: Repository
    lateinit var viewModel: RepoViewModel

    @Before
    fun setUp() {
        viewModel = RepoViewModel()
        repo = mock()
    }

    @Test
    fun getmovie() {
        val gson = Gson()

        val movieList = gson.fromJson(Json.movieJson, MovieModel::class.java)

        val movielivedata = MutableLiveData<MovieModel>()
        movielivedata.value = movieList

        whenever(repo.getMovieLd()).thenReturn(movielivedata)

        val observer :Observer<MovieModel> = mock()
        viewModel.initMovie().observeForever(observer)

        verify(observer).onChanged(movieList)

    }

    @Test
    fun getTvShow() {
        val gson = Gson()

        val tvList = gson.fromJson(Json.tvshowJson, TvShowModel::class.java)
        val tvliveData = MutableLiveData<TvShowModel>()
        tvliveData.value = tvList

        whenever(repo.getTvLd()).thenReturn(tvliveData)

        val observer: Observer<TvShowModel> = mock()

        viewModel.initTvhow().observeForever(observer)

        verify(observer).onChanged(tvList)

    }

}