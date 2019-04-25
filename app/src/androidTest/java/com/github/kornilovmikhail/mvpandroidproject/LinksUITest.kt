package com.github.kornilovmikhail.mvpandroidproject

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.rule.ActivityTestRule
import com.github.kornilovmikhail.mvpandroidproject.ui.MainActivity
import org.hamcrest.core.AllOf.allOf
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class LinksUITest {

    @get:Rule
    val activityTestRule = ActivityTestRule<MainActivity>(MainActivity::class.java)

    @Before
    fun initLoad() {
        Thread.sleep(2000)

        Espresso.onView(ViewMatchers.withId(R.id.rv_events))
            .perform(
                RecyclerViewActions
                    .actionOnItemAtPosition<RecyclerView.ViewHolder>(3, ViewActions.click()))

        Espresso.onView(ViewMatchers.withId(R.id.action_open_links))
            .check(matches(ViewMatchers.isEnabled()))
            .perform(ViewActions.click())
    }

    @Test
    fun testLinksIsDisplayed() {
        Espresso.onView(ViewMatchers.withId(R.id.tv_link_article))
            .check(matches(allOf(ViewMatchers.isDisplayed())))

        Espresso.onView(ViewMatchers.withId(R.id.tv_link_reddit))
            .check(matches(allOf(ViewMatchers.isDisplayed())))

        Espresso.onView(ViewMatchers.withId(R.id.tv_link_wiki))
            .check(matches(allOf(ViewMatchers.isDisplayed())))
    }
}
