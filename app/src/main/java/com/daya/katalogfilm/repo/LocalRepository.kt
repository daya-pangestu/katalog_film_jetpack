package com.daya.katalogfilm.repo

import android.app.Application
import com.daya.katalogfilm.db.KatalogDatabase
import com.daya.katalogfilm.model.favorite.MovieFavModel
import com.daya.katalogfilm.model.favorite.TvShowFavModel

class LocalRepository(var application: Application) {
     var db = KatalogDatabase.getInstance(application)
     var favDao = db.favDao()

    fun getAllMovieFav() = favDao.getAllMovieFav()

    suspend fun insertMovieFav(movieFavModel: MovieFavModel) {
        favDao.insertMovieFav(movieFavModel)
    }

    suspend fun deleteMovieFav(movieFavModel: MovieFavModel) {
        favDao.deleteMovieFav(movieFavModel)
    }

    suspend fun isMovieFavExist(idmovie: Int) = favDao.isMovieExists(idmovie)

    fun getAllTvFav() = favDao.getAllTvShowFav()

   suspend fun insertTvShowFav(tvShowFavModel: TvShowFavModel) = favDao.insertTvShowFav(tvShowFavModel)

    suspend fun deleteTvShowFav(tvShowFavModel: TvShowFavModel) = favDao.deleteTvShowFav(tvShowFavModel)

    suspend fun isTvExists(idTvShow : Int) = favDao.isTvExists(idTvShow)


}