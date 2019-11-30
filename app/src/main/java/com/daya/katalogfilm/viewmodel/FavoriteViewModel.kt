package com.daya.katalogfilm.viewmodel

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.daya.katalogfilm.model.favorite.MovieFavModel
import com.daya.katalogfilm.model.favorite.TvShowFavModel
import com.daya.katalogfilm.repo.LocalRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FavoriteViewModel(private val application: Application) : ViewModel() {

    private val localRepo by lazy { LocalRepository(application) }

    fun getAllMovie(): LiveData<PagedList<MovieFavModel>> {
        return LivePagedListBuilder<Int, MovieFavModel>(localRepo.getAllMovieFav(), 20).build()
    }

    fun insertMovie(movieFavModel: MovieFavModel) = viewModelScope.launch(Dispatchers.IO) {
        localRepo.insertMovieFav(movieFavModel)
    }

    fun deleteMovie(movieFavModel: MovieFavModel) = viewModelScope.launch(Dispatchers.IO) {
        localRepo.deleteMovieFav(movieFavModel)
    }

    fun isMovieFavExist(idmovie: Int) = liveData {
        emit(localRepo.isMovieFavExist(idmovie))
    }

    /*------------------*/

    fun getAllTv(): LiveData<PagedList<TvShowFavModel>> {
        return LivePagedListBuilder<Int,TvShowFavModel>(localRepo.getAllTvFav(),20).build()
    }

    fun insertTv(tvShowFavModel: TvShowFavModel) = viewModelScope.launch(Dispatchers.IO) {
        localRepo.insertTvShowFav(tvShowFavModel)
    }

    fun deleteTv(tvShowFavModel: TvShowFavModel) = viewModelScope.launch(Dispatchers.IO) {
        localRepo.deleteTvShowFav(tvShowFavModel)
    }

    fun isTvFavExist(idmovie: Int) = liveData {
        emit(localRepo.isTvExists(idmovie))
    }
}