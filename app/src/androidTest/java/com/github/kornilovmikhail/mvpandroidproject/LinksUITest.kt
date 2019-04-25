package com.github.kornilovmikhail.mvpandroidproject

import androidx.test.espresso.Espresso
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import org.hamcrest.core.AllOf.allOf
import org.junit.Before
import org.junit.Test

class LinksUITest {

    @Before
    fun initLoad() {
        Thread.sleep(2000)
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
