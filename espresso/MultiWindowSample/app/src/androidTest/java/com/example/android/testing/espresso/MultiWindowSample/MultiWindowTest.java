/*
 * Copyright 2014, The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.android.testing.espresso.MultiWindowSample;

import android.test.ActivityInstrumentationTestCase2;
import android.test.suitebuilder.annotation.LargeTest;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.action.ViewActions.typeTextIntoFocusedView;
import static android.support.test.espresso.assertion.ViewAssertions.doesNotExist;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.RootMatchers.withDecorView;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;

/**
 * Demonstrates dealing with multiple windows.
 * <p/>
 * Android's Window system allows multiple view hierarchies to layer on top of each other.
 * <p/>
 * A real world analogy would be an overhead projector with multiple transparencies placed
 * on top of each other. Each Window is a transparency, and what is drawn on top of this
 * transparency is the view hierarchy.
 * <p/>
 * By default Espresso uses a heuristic to guess which Window you intend to interact with.
 * This heuristic is normally 'good enough' however if you want to interact with a Window
 * that it does not select then you'll have to swap in your own root window matcher.
 * Initially we only have 1 window, but by typing into the auto complete text view another
 * window will be layered on top of the screen. Espresso ignores this layer because it is
 * not connected to the keyboard/ime.
 * <p/>
 * Espresso provides the ability to switch the default window matcher used in both onView and onData
 * interactions.
 *
 * @see android.support.test.espresso.Espresso#onView
 * @see android.support.test.espresso.Espresso#onData
 */
@LargeTest
public class MultiWindowTest extends ActivityInstrumentationTestCase2<SuggestActivity> {

    public MultiWindowTest() {
        super(SuggestActivity.class);
    }

    @Override
    public void setUp() throws Exception {
        super.setUp();
        // Espresso will not launch our activity for us, we must launch it via getActivity().
        getActivity();
    }

    public void testAutoCompleteTextView_twoSuggestions() {
        // Type "So" to trigger two suggestions.
        onView(withId(R.id.auto_complete_text_view))
                .perform(typeText("So"));

        // Check that both suggestions are displayed.
        onView(withText("South China Sea"))
                .inRoot(withDecorView(not(is(getActivity().getWindow().getDecorView()))))
                .check(matches(isDisplayed()));
        onView(withText("Southern Ocean"))
                .inRoot(withDecorView(not(is(getActivity().getWindow().getDecorView()))))
                .check(matches(isDisplayed()));
    }

    public void testAutoCompleteTextView_oneSuggestion() {
        // Type "South" to trigger one suggestion.
        onView(withId(R.id.auto_complete_text_view))
                .perform(typeTextIntoFocusedView("South "));

        // Should be displayed
        onView(withText("South China Sea"))
                .inRoot(withDecorView(not(is(getActivity().getWindow().getDecorView()))))
                .check(matches(isDisplayed()));

        // Should not be displayed.
        onView(withText("Southern Ocean"))
                .inRoot(withDecorView(not(is(getActivity().getWindow().getDecorView()))))
                .check(doesNotExist());
    }

    public void testAutoCompleteTextView_clickAndCheck() {
        // Type text into the text view
        onView(withId(R.id.auto_complete_text_view))
                .perform(typeTextIntoFocusedView("South "));

        // Tap on a suggestion.
        onView(withText("South China Sea"))
                .inRoot(withDecorView(not(is(getActivity().getWindow().getDecorView()))))
                .perform(click());

        // By clicking on the auto complete term, the text should be filled in.
        onView(withId(R.id.auto_complete_text_view))
                .check(matches(withText("South China Sea")));
    }

    public void testAutoCompleteTextView_onDataClickAndCheck() {
        // NB: The autocompletion box is implemented with a ListView, so the preferred way
        // to interact with it is onData(). We can use inRoot here too!
        onView(withId(R.id.auto_complete_text_view))
                .perform(typeText("S"));

        // This is useful because some of the completions may not be part of the View Hierarchy
        // unless you scroll around the list.
        onData(allOf(instanceOf(String.class), is("Baltic Sea")))
                .inRoot(withDecorView(not(is(getActivity().getWindow().getDecorView()))))
                .perform(click());

        // The text should be filled in.
        onView(withId(R.id.auto_complete_text_view))
                .check(matches(withText("Baltic Sea")));
    }

}


