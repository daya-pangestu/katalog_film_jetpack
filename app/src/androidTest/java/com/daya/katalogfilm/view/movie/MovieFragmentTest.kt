package com.daya.katalogfilm.view.movie

import androidx.test.rule.ActivityTestRule
import org.junit.Rule
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.daya.katalogfilm.R
import com.daya.katalogfilm.SingleFragmentActivity
import com.daya.katalogfilm.utils.RecyclerViewItemCountAssertion
import org.junit.Before
import org.junit.Test


class MovieFragmentTest{

    @get:Rule
    var activityRule: ActivityTestRule<SingleFragmentActivity> =
        ActivityTestRule(SingleFragmentActivity::class.java)

    private val movieFragment = MovieFragment()

    @Before
    fun setUp() {
        activityRule.activity.setFragment(movieFragment)
    }

    @Test
    fun loadMovie() {
        onView(withId(R.id.fMovieRv)).check(matches(isDisplayed()))
        onView(withId(R.id.fMovieRv)).check(RecyclerViewItemCountAssertion(20))
    }
}

