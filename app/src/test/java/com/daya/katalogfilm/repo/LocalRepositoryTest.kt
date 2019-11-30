package com.daya.katalogfilm.repo

import android.app.Application
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.paging.DataSource
import androidx.paging.PagedList
import com.daya.katalogfilm.db.FavoriteDao
import com.daya.katalogfilm.db.KatalogDatabase
import com.daya.katalogfilm.model.favorite.MovieFavModel
import com.daya.katalogfilm.model.favorite.TvShowFavModel
import com.daya.katalogfilm.util.FakeRepository
import com.daya.katalogfilm.util.mockPagedList
import com.daya.katalogfilm.viewmodel.FavoriteViewModel
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import org.junit.Test

import org.junit.Assert.*
import org.junit.Rule

class LocalRepositoryTest {
    @get:Rule
    var rule = InstantTaskExecutorRule()

    private var local: LocalRepository = mock()
    private val app = mock<Application>()
    private val db = mock<KatalogDatabase>()
    private val dao = mock<FavoriteDao>()


    private  var favViewModel: FavoriteViewModel = FavoriteViewModel(app)

    val listdummymovFav = FakeRepository.generateListDummyFavMovie()
    val listdummyTvFav = FakeRepository.generateListDummyFavTv()

    @Test
    fun getFavMovie() {
        val dataSourceFactory: DataSource.Factory<Int, MovieFavModel> = mock()
        whenever(local.getAllMovieFav()).thenReturn(dataSourceFactory)
        val result: PagedList<MovieFavModel> = mockPagedList(listdummymovFav)
        local.getAllMovieFav()

        verify(local).getAllMovieFav()
        assertNotNull(result)
        assertEquals(listdummymovFav.size, result.size)
    }

    @Test
    fun getFavTv() {

        val dataSourceFactory: DataSource.Factory<Int, TvShowFavModel> = mock()
        whenever(local.getAllTvFav()).thenReturn(dataSourceFactory)
        local.getAllTvFav()
        val result: PagedList<TvShowFavModel> = mockPagedList(listdummyTvFav)


        verify(local).getAllTvFav()
        assertNotNull(result)
        assertEquals(listdummymovFav.size, result.size)

    }
}