package com.daya.katalogfilm.view.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.rule.ActivityTestRule
import com.daya.katalogfilm.R
import com.daya.katalogfilm.SingleFragmentActivity
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import com.daya.katalogfilm.model.favorite.MovieFavModel
import com.daya.katalogfilm.model.movie.MovieModel
import com.daya.katalogfilm.utils.Json
import com.daya.katalogfilm.viewmodel.DetailViewModel
import com.google.gson.Gson

class DetailFragmentTest{

    @get:Rule
    val activityRule: ActivityTestRule<SingleFragmentActivity> =
     ActivityTestRule<SingleFragmentActivity>(SingleFragmentActivity::class.java)

    @get:Rule
    val task = InstantTaskExecutorRule()

    val detailFragment = DetailMovieFragment()

    @Before
    fun setUp() {
        activityRule.activity.setFragment(detailFragment)
    }

    @Test
    fun loadDetailMovie() {
        onView(withId(R.id.detail_movie_text_judul)).check(matches(isDisplayed()))
        onView(withId(R.id.detail_movie_img_poster)).check(matches(isDisplayed()))
        onView(withId(R.id.detail_moviel_btn_fav)).check(matches(isDisplayed()))
    }


}