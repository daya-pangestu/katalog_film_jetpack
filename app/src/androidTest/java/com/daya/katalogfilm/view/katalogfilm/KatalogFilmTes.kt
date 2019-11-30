package com.daya.katalogfilm.view.katalogfilm

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.daya.katalogfilm.R
import com.daya.katalogfilm.SingleFragmentActivity
import com.daya.katalogfilm.model.favorite.MovieFavModel
import com.daya.katalogfilm.model.movie.MovieModel
import com.daya.katalogfilm.utils.Json
import com.daya.katalogfilm.utils.RecyclerViewItemCountAssertion
import com.daya.katalogfilm.view.detail.DetailMovieFragment
import com.daya.katalogfilm.view.detail.DetailTvShowFragment
import com.daya.katalogfilm.view.movie.MovieFragment
import com.google.gson.Gson
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify


@RunWith(AndroidJUnit4::class)
class KatalogFilmTes {
    @get:Rule
    var activityRule: ActivityTestRule<SingleFragmentActivity> =
        ActivityTestRule<SingleFragmentActivity>(SingleFragmentActivity::class.java)


    val movieFragment = MovieFragment()

    val mockNavController = mock(NavController::class.java)

    val gson = Gson()
    val fakeDataMovie = gson.fromJson(Json.movieJson, MovieModel::class.java).results[0]
    val fakeMovieFav = MovieFavModel(
        id = fakeDataMovie.id,
        rating = fakeDataMovie.vote_average,
        date = fakeDataMovie.release_date,
        backgrop_path = fakeDataMovie.backdrop_path,
        poster_path = fakeDataMovie.poster_path,
        description = fakeDataMovie.poster_path,
        title = fakeDataMovie.title
    )


    @Before
    fun setUp() {
        activityRule.activity.setFragment(movieFragment)
    }

    @Test
    fun navigateFragmentTodetail() {
        onView(withId(R.id.fMovieRv)).check(matches(isDisplayed()))
        val movieScenario = launchFragmentInContainer<MovieFragment>()
        movieScenario.onFragment { fragment ->
            Navigation.setViewNavController(fragment.requireView(), mockNavController)
        }

       onView(withId(R.id.fMovieRv)).check(RecyclerViewItemCountAssertion(20))

       onView(withId(R.id.fMovieRv)).perform(
           actionOnItemAtPosition<RecyclerView.ViewHolder>(
               0,
               click()
           )
       )
       verify(mockNavController).navigate(R.id.action_movieFragment_to_detailFragment)
    }

    @Test
    fun load_detailMovie() {
        val detailFragment = DetailMovieFragment()
        activityRule.activity.replaceFragment(detailFragment)

        onView(withId(R.id.detail_movie_text_judul)).check(matches(isDisplayed()))
        onView(withId(R.id.detail_movie_img_poster)).check(matches(isDisplayed()))

        onView(withId(R.id.detail_moviel_btn_fav)).check(matches(isDisplayed()))

        onView(withId(R.id.detail_moviel_btn_fav)).check(matches(isDisplayed()))

        onView(withId(R.id.detail_moviel_btn_fav)).perform(click())

    }

    @Test
    fun load_detailTv() {
        val detailFragment = DetailTvShowFragment()
        activityRule.activity.replaceFragment(detailFragment)

        onView(withId(R.id.detail_tvl_text_judul)).check(matches(isDisplayed()))
        onView(withId(R.id.detail_tv_img_poster)).check(matches(isDisplayed()))

        onView(withId(R.id.detail_tv_btn_fav)).check(matches(isDisplayed()))

        onView(withId(R.id.detail_tv_btn_fav)).perform(click())
    }
}
