package com.mahesaiqbal.moviecatalogue.ui.detail

import android.content.Intent
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.rule.ActivityTestRule
import com.mahesaiqbal.moviecatalogue.R
import com.mahesaiqbal.moviecatalogue.utils.EspressoIdlingResource
import org.junit.After
import org.junit.Before

import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test

class DetailTVActivityTest {

    @Rule
    @JvmField
    var tvActivityRule: ActivityTestRule<DetailTVActivity> =
        object : ActivityTestRule<DetailTVActivity>(DetailTVActivity::class.java) {
            override fun getActivityIntent(): Intent {
                val targetContext = InstrumentationRegistry.getInstrumentation().targetContext
                val result = Intent(targetContext, DetailTVActivity::class.java)
                result.putExtra("tv_id", 60735)
                return result
            }
        }

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
        onView(ViewMatchers.withId(R.id.tv_title)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        onView(ViewMatchers.withId(R.id.tv_title))
            .check(ViewAssertions.matches(ViewMatchers.withText("The Flash")))
    }
}