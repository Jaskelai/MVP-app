package com.github.kornilovmikhail.mvpandroidproject

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.isEnabled
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import com.github.kornilovmikhail.mvpandroidproject.ui.MainActivity
import org.hamcrest.core.AllOf.allOf
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class DetailsUITest {

    @get:Rule
    val activityTestRule = ActivityTestRule<MainActivity>(MainActivity::class.java)

    @Before
    fun initLoad() {
        Thread.sleep(2000)
        onView(withId(R.id.rv_events))
            .perform(RecyclerViewActions
                .actionOnItemAtPosition<RecyclerView.ViewHolder>(3, click()))
    }

    @Test
    fun testToolbarButton() {
        onView(withId(R.id.action_open_links))
            .check(matches(isEnabled()))
            .perform(click())

        onView(withId(R.id.tv_link_article)).check(matches(allOf(ViewMatchers.isDisplayed())))
    }
}
