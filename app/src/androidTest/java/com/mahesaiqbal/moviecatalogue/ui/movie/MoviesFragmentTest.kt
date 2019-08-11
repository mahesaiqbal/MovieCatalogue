package com.mahesaiqbal.moviecatalogue.ui.movie

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions.swipeRight
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.mahesaiqbal.moviecatalogue.R
import com.mahesaiqbal.moviecatalogue.ui.MainActivity
import com.mahesaiqbal.moviecatalogue.utils.EspressoIdlingResource
import com.mahesaiqbal.moviecatalogue.utils.RecyclerViewItemCountAssertion
import org.junit.After
import org.junit.Before

import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MoviesFragmentTest {

    @Rule
    @JvmField
    var activityRule = ActivityTestRule(MainActivity::class.java)

    @Before
    fun setUp() {
        IdlingRegistry.getInstance().register(EspressoIdlingResource.getEspressoIdlingResource())
    }

    @After
    fun tearDown() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.getEspressoIdlingResource())
    }

    @Test
    fun loadMovies() {
        onView(withId(R.id.view_pager)).check(matches(isDisplayed()))
        onView(withId(R.id.view_pager)).perform(swipeRight())
        onView(withId(R.id.rv_movies)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_movies)).check(RecyclerViewItemCountAssertion(20))
    }
}