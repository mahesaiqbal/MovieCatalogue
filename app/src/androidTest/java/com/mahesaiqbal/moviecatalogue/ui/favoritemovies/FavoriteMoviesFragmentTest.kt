package com.mahesaiqbal.moviecatalogue.ui.favoritemovies

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions.swipeLeft
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

import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class FavoriteMoviesFragmentTest {

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
    fun loadFavMovies() {
        // Check the amount of data stored on the list of favorite movies
        // If the list is empty, you can comment the code below
        onView(withId(R.id.view_pager)).check(matches(isDisplayed()))
        onView(withId(R.id.view_pager)).perform(swipeLeft())
        onView(withId(R.id.view_pager)).perform(swipeLeft())
        onView(withId(R.id.rv_fav_movies)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_fav_movies)).check(RecyclerViewItemCountAssertion(1))
    }
}