package com.daya.katalogfilm.view.favorite

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.rule.ActivityTestRule
import com.daya.katalogfilm.R

import com.daya.katalogfilm.SingleFragmentActivity
import com.daya.katalogfilm.view.favorite.host.FavoriteFragment
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class FavoriteFragmentTest {

    @get:Rule
    var activityRule: ActivityTestRule<SingleFragmentActivity> =
        ActivityTestRule(SingleFragmentActivity::class.java)

    private val favHostFragment = FavoriteFragment()


    @Before
    fun setUp() {
        activityRule.activity.setFragment(favHostFragment)
    }

    @Test
    fun checkView() {
        onView(withId(R.id.f_fav_tab)).check(matches(isDisplayed()))
        onView(withId(R.id.f_fav_viewpager)).check(matches(isDisplayed()))

    }
}