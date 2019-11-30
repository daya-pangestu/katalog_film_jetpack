package com.daya.katalogfilm.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.daya.katalogfilm.repo.LocalRepository
import com.daya.katalogfilm.util.FakeRepository
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import org.junit.Test

import org.junit.Before
import org.junit.Rule
import com.daya.katalogfilm.model.favorite.MovieFavModel
import com.daya.katalogfilm.model.favorite.TvShowFavModel
import com.nhaarman.mockitokotlin2.mock


class FavoriteViewModelTest {

    @get:Rule
    var rule = InstantTaskExecutorRule()

    private var local: LocalRepository = mock()
    lateinit var favViewModel: FavoriteViewModel

    val listdummymovFav = FakeRepository.generateListDummyFavMovie()



    @Before
    fun setUp() {
        favViewModel  = mock()
    }

    @Test
    fun getAllMoviefavTest() {
        val dummyMovFav = MutableLiveData<PagedList<MovieFavModel>>()
        val pagedList :PagedList<MovieFavModel> = mock()
        dummyMovFav.value = pagedList

        whenever(favViewModel.getAllMovie()).thenReturn(dummyMovFav)

        val observer: Observer<PagedList<MovieFavModel>> = mock()
        favViewModel.getAllMovie().observeForever(observer)

        verify(favViewModel).getAllMovie()

    }

    @Test
    fun getAllTvfavTest() {
        val dummyTvFav = MutableLiveData<PagedList<TvShowFavModel>>()
        val pagedList :PagedList<TvShowFavModel> = mock()
        dummyTvFav.value = pagedList

        whenever(favViewModel.getAllTv()).thenReturn(dummyTvFav)

        val observer: Observer<PagedList<TvShowFavModel>> = mock()
        favViewModel.getAllTv().observeForever(observer)

        verify(favViewModel).getAllTv()
    }
}