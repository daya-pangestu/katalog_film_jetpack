package com.daya.katalogfilm.view.tvshow

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.rule.ActivityTestRule
import com.daya.katalogfilm.R
import com.daya.katalogfilm.SingleFragmentActivity
import com.daya.katalogfilm.SingleTestFragmentActivity
import com.daya.katalogfilm.utils.RecyclerViewItemCountAssertion
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class TvsShowFragmentTest {
    @get:Rule
    var activityRule: ActivityTestRule<SingleFragmentActivity> =
        ActivityTestRule(SingleFragmentActivity::class.java)

    private val tvsShowFragment = TvsShowFragment()

    @Before
    fun setUp() {
        activityRule.activity.setFragment(tvsShowFragment)
    }

    @Test
    fun loadMovie() {
        onView(withId(R.id.ftvShowRv)).check(matches(isDisplayed()))
        onView(withId(R.id.ftvShowRv)).check(RecyclerViewItemCountAssertion(20))
    }
}