package com.daya.katalogfilm.db

import androidx.paging.DataSource
import androidx.room.*
import com.daya.katalogfilm.model.favorite.MovieFavModel
import com.daya.katalogfilm.model.favorite.TvShowFavModel

@Dao
interface FavoriteDao {

    @Query("SELECT * FROM moviefavmodel ORDER BY id ASC ")
    fun getAllMovieFav(): DataSource.Factory<Int, MovieFavModel>

    @Insert
    suspend  fun insertMovieFav(movieFavModel: MovieFavModel)

    @Delete
    suspend fun deleteMovieFav(movieFavModel: MovieFavModel)

    @Update
    suspend fun updateMovieFav(movieFavModel: MovieFavModel)

    @Query("SELECT * FROM moviefavmodel WHERE id = :idmovie ")
    suspend fun isMovieExists(idmovie :Int) : MovieFavModel?

//    /*//////////////////////////*/

    @Query("SELECT * FROM tvshowfavmodel ORDER BY id ASC ")
    fun getAllTvShowFav(): DataSource.Factory<Int, TvShowFavModel>

    @Insert
    suspend fun insertTvShowFav(tvshowfavmodel: TvShowFavModel)

    @Delete
    suspend fun deleteTvShowFav(tvshowfavmodel: TvShowFavModel)

    @Update
    suspend fun updateTvShowFav(tvshowfavmodel: TvShowFavModel)


    @Query("SELECT * FROM tvshowfavmodel WHERE id = :idmovie ")
    suspend fun isTvExists(idmovie :Int) : TvShowFavModel?
}