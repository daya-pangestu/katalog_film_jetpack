package com.daya.katalogfilm.view.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.rule.ActivityTestRule
import com.daya.katalogfilm.R
import com.daya.katalogfilm.SingleFragmentActivity
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class DetailTvShowFragmentTest {
    @get:Rule
    var activityRule: ActivityTestRule<SingleFragmentActivity> =
        ActivityTestRule(SingleFragmentActivity::class.java)

    @get:Rule
    val task = InstantTaskExecutorRule()

    val detailTvFragment = DetailTvShowFragment()


    @Before
    fun setUp() {
        activityRule.activity.setFragment(detailTvFragment)
    }

    @Test
    fun loadDetailTv() {
        onView(withId(R.id.detail_tvl_text_judul)).check(matches(isDisplayed()))
        onView(withId(R.id.detail_tv_img_poster)).check(matches(isDisplayed()))
        onView(withId(R.id.detail_tv_btn_fav)).check(matches(isDisplayed()))
    }

}