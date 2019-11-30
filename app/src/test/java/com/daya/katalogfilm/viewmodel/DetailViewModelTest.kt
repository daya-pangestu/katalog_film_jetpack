package com.daya.katalogfilm.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.daya.katalogfilm.model.movie.MovieModel
import com.daya.katalogfilm.repo.Json
import com.daya.katalogfilm.repo.Repository
import com.google.gson.Gson
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class DetailViewModelTest {
    @get:Rule
    val task = InstantTaskExecutorRule()

   lateinit var viewModel: DetailViewModel


    @Before
    fun setUp() {
        viewModel = DetailViewModel()
    }

    @Test
    fun testaddMovieValue() {
        viewModel.getMoviee().observeForever{
            assertNotNull(it)
        }
    }

    @Test
    fun testaddTvValue() {
        viewModel.getMoviee().observeForever{
            assertNotNull(it)
        }
    }

}
