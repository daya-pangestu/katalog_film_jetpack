package com.daya.katalogfilm.util

import com.daya.katalogfilm.model.favorite.MovieFavModel
import com.daya.katalogfilm.model.favorite.TvShowFavModel
import com.daya.katalogfilm.model.movie.MovieModel
import com.daya.katalogfilm.model.tv_show.TvShowModel
import com.daya.katalogfilm.repo.Json
import com.google.gson.Gson

object FakeRepository {
    private val gson = Gson()

    fun generateListDummyFavMovie(): MutableList<MovieFavModel> {
        val movie = gson.fromJson(Json.movieJson, MovieModel::class.java)
        val movieresult = movie.results
        val listmovFav = mutableListOf<MovieFavModel>()
        repeat(10){
            val dummyFavMovie = MovieFavModel(
                id = movieresult[it].id,
                title = movieresult[it].title,
                date = movieresult[it].release_date,
                description = movieresult[it].overview,
                poster_path = movieresult[it].poster_path,
                backgrop_path = movieresult[it].backdrop_path,
                rating = movieresult[it].vote_average
            )
            listmovFav.add(dummyFavMovie)

        }
        return listmovFav
    }

    fun generateListDummyFavTv(): MutableList<TvShowFavModel> {
        val tvshow = gson.fromJson(Json.tvshowJson, TvShowModel::class.java)
        val tvshowresult = tvshow.results
        val listTvFav = mutableListOf<TvShowFavModel>()
        repeat(10){
            val dummyFavTv = TvShowFavModel(
                id = tvshowresult[it].id,
                title = tvshowresult[it].name,
                date = tvshowresult[it].first_air_date,
                description = tvshowresult[it].overview,
                poster_path = tvshowresult[it].poster_path,
                backdrop_path = tvshowresult[it].backdrop_path,
                rating = tvshowresult[it].vote_average
            )
            listTvFav.add(dummyFavTv)

        }
        return listTvFav
    }
}