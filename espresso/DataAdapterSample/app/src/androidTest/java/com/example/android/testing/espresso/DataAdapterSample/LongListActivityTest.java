/*
 * Copyright (C) 2014 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.android.testing.espresso.DataAdapterSample;

import android.support.test.espresso.DataInteraction;
import android.support.test.espresso.Espresso;
import android.support.test.espresso.action.ViewActions;
import android.support.test.espresso.matcher.ViewMatchers;

import android.test.ActivityInstrumentationTestCase2;
import android.test.suitebuilder.annotation.LargeTest;

import java.util.Map;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.doesNotExist;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isChecked;
import static android.support.test.espresso.matcher.ViewMatchers.isCompletelyDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasEntry;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;

/**
 * Tests to verify that the behavior of {@link LongListActivity} is correct.
 * <p>
 * Note that in order to scroll the list you shouldn't use {@link ViewActions#scrollTo()} as
 * {@link Espresso#onData(org.hamcrest.Matcher)} handles scrolling.</p>
 *
 * @see #onRow(String)
 */
@LargeTest
public class LongListActivityTest extends ActivityInstrumentationTestCase2<LongListActivity> {

    private static final String TEXT_ITEM_30 = "item: 30";
    private static final String TEXT_ITEM_30_SELECTED = "30";

    private static final String TEXT_ITEM_60 ="item: 60";

    // Match the last item by matching its text.
    private static final String LAST_ITEM_ID ="item: 99";

    public LongListActivityTest() {
        super(LongListActivity.class);
    }

    @Override
    public void setUp() throws Exception {
        super.setUp();
        // For each test method invocation, the Activity will not actually be created
        // until the first time this method is called.
        getActivity();
    }

    /**
     * Test that the list is long enough for this sample, the last item shouldn't appear.
     */
    public void testLastItem_NotDisplayed() {
        // Last item should not exist if the list wasn't scrolled down.
        onView(withText(LAST_ITEM_ID)).check(doesNotExist());
    }

    /**
     * Check that the item is created. onData() takes care of scrolling.
     */
    public void testList_Scrolls() {
        onRow(LAST_ITEM_ID).check(matches(isCompletelyDisplayed()));
    }

    /**
     * Clicks on a row and checks that the activity detected the click.
     */
    public void testRow_Click() {
        // Click on one of the rows.
        onRow(TEXT_ITEM_30).onChildView(withId(R.id.rowContentTextView)).perform(click());

        // Check that the activity detected the click on the first column.
        onView(ViewMatchers.withId(R.id.selection_row_value))
                .check(matches(withText(TEXT_ITEM_30_SELECTED)));
    }

    /**
     * Checks that a toggle button is checked after clicking on it.
     */
    public void testToggle_Click() {
        // Click on a toggle button.
        onRow(TEXT_ITEM_30).onChildView(withId(R.id.rowToggleButton)).perform(click());

        // Check that the toggle button is checked.
        onRow(TEXT_ITEM_30).onChildView(withId(R.id.rowToggleButton)).check(matches(isChecked()));
    }

    /**
     * Make sure that clicking on the toggle button doesn't trigger a click on the row.
     */
    public void testToggle_ClickDoesntPropagate() {
        // Click on one of the rows.
        onRow(TEXT_ITEM_30).onChildView(withId(R.id.rowContentTextView)).perform(click());

        // Click on the toggle button, in a different row.
        onRow(TEXT_ITEM_60).onChildView(withId(R.id.rowToggleButton)).perform(click());

        // Check that the activity didn't detect the click on the first column.
        onView(ViewMatchers.withId(R.id.selection_row_value))
                .check(matches(withText(TEXT_ITEM_30_SELECTED)));
    }

    /**
     * Uses {@link Espresso#onData(org.hamcrest.Matcher)} to get a reference to a specific row.
     * <p>
     * Note: A custom matcher can be used to match the content and have more readable code.
     * See the Custom Matcher Sample.
     * </p>
     *
     * @param str the content of the field
     * @return a {@link DataInteraction} referencing the row
     */
    private static DataInteraction onRow(String str) {
        return onData(allOf(
                is(instanceOf(Map.class)), hasEntry(equalTo(LongListActivity.ROW_TEXT), is(str))));
    }
}