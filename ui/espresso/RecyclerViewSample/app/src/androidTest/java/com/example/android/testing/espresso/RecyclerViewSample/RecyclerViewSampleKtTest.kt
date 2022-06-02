package com.example.android.testing.espresso.RecyclerViewSample

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.PerformException
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.activityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.TypeSafeMatcher
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@LargeTest
class RecyclerViewSampleKtTest {

    /**
     * Use {@link ActivityScenario} to create and launch the activity under test. This is a
     * replacement for {@link androidx.test.rule.ActivityTestRule}.
     */
    @get:Rule
    var activityScenarioRule = activityScenarioRule<MainActivity>()

    @Test(expected = PerformException::class)
    fun itemWithText_doesNotExist() {
        // Attempt to scroll to an item that contains the special text.
        onView(withId(R.id.recyclerView))
            // scrollTo will fail the test if no item matches.
            .perform(RecyclerViewActions.scrollTo<CustomAdapter.ViewHolder>(
                    hasDescendant(withText("not in the list"))
            ))
    }

    @Test
    fun scrollToItemBelowFold_checkItsText() {
        // First scroll to the position that needs to be matched and click on it.
        onView(withId(R.id.recyclerView))
            .perform(RecyclerViewActions.actionOnItemAtPosition<CustomAdapter.ViewHolder>(ITEM_BELOW_THE_FOLD, click()))

        // Match the text in an item below the fold and check that it's displayed.
        val itemElementText = "This is element #${ITEM_BELOW_THE_FOLD}"
        onView(withText(itemElementText)).check(matches(isDisplayed()))
    }

    @Test
    fun itemInMiddleOfList_hasSpecialText() {
        // First, scroll to the view holder using the isInTheMiddle matcher.
        onView(withId(R.id.recyclerView))
            .perform(RecyclerViewActions.scrollToHolder(isInTheMiddle()))

        // Check that the item has the special text.
        val middleElementText = "This is the middle!"
        onView(withText(middleElementText)).check(matches(isDisplayed()))
    }

    /**
     * Matches the {@link CustomAdapter.ViewHolder}s in the middle of the list.
     */
    private fun isInTheMiddle(): Matcher<CustomAdapter.ViewHolder> {
        return object: TypeSafeMatcher<CustomAdapter.ViewHolder>() {
            override fun matchesSafely(customHolder: CustomAdapter.ViewHolder): Boolean {
                return customHolder.isInTheMiddle
            }

            override fun describeTo(description: Description) {
                description.appendText("item in the middle")
            }
        }
    }

    companion object {
        val ITEM_BELOW_THE_FOLD = 40
    }

}