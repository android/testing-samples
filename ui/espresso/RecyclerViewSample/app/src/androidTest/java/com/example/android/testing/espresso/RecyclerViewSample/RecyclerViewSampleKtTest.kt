package com.example.android.testing.espresso.RecyclerViewSample

import android.content.Context
import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.ApplicationProvider.getApplicationContext
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.TypeSafeMatcher
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

/**
 * The kotlin equivalent to the [RecyclerViewSampleTest]
 */
@RunWith(AndroidJUnit4::class)
@LargeTest
class RecyclerViewSampleKtTest {

    private val ITEM_BELOW_THE_FOLD = 40

    /**
     * Use [ActivityScenario] to create and launch the activity under test. This is a
     * replacement for [androidx.test.rule.ActivityTestRule].
     */
    @Before
    fun launchActivity() {
        ActivityScenario.launch(MainActivity::class.java)
    }

    @Test
    fun scrollToItemBelowFold_checkItsText() {
        // First scroll to the position that needs to be matched and click on it.
        onView(ViewMatchers.withId(R.id.recyclerView))
                .perform(RecyclerViewActions.actionOnItemAtPosition<CustomAdapter.ViewHolder>(
                        ITEM_BELOW_THE_FOLD, click()))

        // Match the text in an item below the fold and check that it's displayed.
        val itemElementText = getApplicationContext<Context>().resources.getString(
                R.string.item_element_text) + ITEM_BELOW_THE_FOLD.toString()
        onView(ViewMatchers.withText(itemElementText)).check(matches(isDisplayed()))
    }

    @Test
    fun itemInMiddleOfList_hasSpecialText() {
        // First, scroll to the view holder using the isInTheMiddle matcher.
        onView(ViewMatchers.withId(R.id.recyclerView))
                .perform(RecyclerViewActions.scrollToHolder(isInTheMiddle()))

        // Check that the item has the special text.
        val middleElementText = getApplicationContext<Context>().resources.getString(R.string.middle)
        onView(ViewMatchers.withText(middleElementText)).check(matches(isDisplayed()))
    }

    /**
     * Matches the [CustomAdapter.ViewHolder]s in the middle of the list.
     */
    private fun isInTheMiddle(): Matcher<CustomAdapter.ViewHolder> {
        return object : TypeSafeMatcher<CustomAdapter.ViewHolder>() {
            override fun matchesSafely(customHolder: CustomAdapter.ViewHolder): Boolean {
                return customHolder.isInTheMiddle
            }

            override fun describeTo(description: Description) {
                description.appendText("item in the middle")
            }
        }
    }
}