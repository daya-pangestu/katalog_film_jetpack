package com.daya.katalogfilm.utils

import com.daya.katalogfilm.model.favorite.MovieFavModel
import com.daya.katalogfilm.model.movie.MovieModel
import com.daya.katalogfilm.repo.Json
import com.google.gson.Gson

object FakeRepository {
    private val gson = Gson()
    val movie = gson.fromJson(Json.movieJson, MovieModel::class.java)


    fun generateListDummyFavMovie(): MutableList<MovieFavModel> {
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

    fun genereateDummyFavMovie(): MovieFavModel {
        val movie = movie.results[0]

        val dummyFavMovie = MovieFavModel(
            id = movie.id,
            title = movie.title,
            date = movie.release_date,
            description = movie.overview,
            poster_path = movie.poster_path,
            backgrop_path = movie.backdrop_path,
            rating = movie.vote_average
        )

        return dummyFavMovie
    }
}