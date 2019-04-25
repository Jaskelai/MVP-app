package com.github.kornilovmikhail.mvpandroidproject

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.contrib.RecyclerViewActions.scrollToPosition
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import com.github.kornilovmikhail.mvpandroidproject.ui.MainActivity
import org.hamcrest.core.AllOf.allOf
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ListUITest {

    @get:Rule
    val activityTestRule = ActivityTestRule<MainActivity>(MainActivity::class.java)

    @Before
    fun initLoad() {
        Thread.sleep(2000)
    }

    @Test
    fun testToolbarButton() {
        onView(withId(R.id.action_pagination)).check(matches(isEnabled())).perform(click())
        onView(withId(R.id.edt_pagination)).check(matches(allOf(isDisplayed())))
    }

    @Test
    fun testRecyclerViewIsDisplayed() {
        onView(withId(R.id.rv_events)).check(matches(isDisplayed()))
    }

    @Test
    fun testRecyclerViewIsScrollable() {
        onView(withId(R.id.rv_events)).perform(scrollToPosition<RecyclerView.ViewHolder>(6))
            .perform(scrollToPosition<RecyclerView.ViewHolder>(10))
            .perform(scrollToPosition<RecyclerView.ViewHolder>(2))
    }

    @Test
    fun testRecyclerViewClickOnItem() {
        onView(withId(R.id.rv_events))
            .perform(actionOnItemAtPosition<RecyclerView.ViewHolder>(3, click()))
        onView(withId(R.id.tv_title_details_activity)).check(matches(allOf(isDisplayed())))
    }
}
