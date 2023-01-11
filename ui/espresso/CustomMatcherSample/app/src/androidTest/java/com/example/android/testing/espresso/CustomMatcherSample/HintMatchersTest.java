/*
 * Copyright 2015, The Android Open Source Project
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

package com.example.android.testing.espresso.CustomMatcherSample;

import static androidx.test.core.app.ApplicationProvider.getApplicationContext;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.anyOf;
import static org.hamcrest.Matchers.endsWith;
import static org.hamcrest.Matchers.not;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.annotation.LooperMode;
import org.robolectric.annotation.TextLayoutMode;

/**
 * Tests for {@link MainActivity} showcasing the use of custom matchers (see
 * {@link HintMatcher#withHint}).
 */
@RunWith(AndroidJUnit4.class)
@LargeTest
// Configure Robolectric to use the more realistic text layout and threading model.
// These annotations can be removed once this is default behavior in Robolectric
@TextLayoutMode(TextLayoutMode.Mode.REALISTIC)
@LooperMode(LooperMode.Mode.PAUSED)
public class HintMatchersTest {

    private static final String INVALID_STRING_TO_BE_TYPED = "Earl Grey";

    private static final String COFFEE_ENDING = "coffee?";

    private static final String COFFEE_INVALID_ENDING = "tea?";

    // A valid string with a valid ending
    private String mStringWithValidEnding;

    // A valid string from the coffee preparations
    private String mValidStringToBeTyped;

    /**
     * {@link ActivityScenarioRule} will create and launch of the activity for you.
     */
    @Rule public ActivityScenarioRule<MainActivity> activityScenarioRule =
        new ActivityScenarioRule<MainActivity>(MainActivity.class);

    @Before
    public void initValidStrings() {
        // Produce a string with valid ending.
        mStringWithValidEnding = "Random " + MainActivity.VALID_ENDING;

        // Get one of the available coffee preparations.
        mValidStringToBeTyped = MainActivity.COFFEE_PREPARATIONS.get(0);
    }

    /**
     * Uses a custom matcher {@link HintMatcher#withHint}, with a {@link String} as the argument.
     */
    @Test
    public void hint_isDisplayedInEditText() {
        String hintText = getApplicationContext().getResources().getString(R.string.hint);

        onView(withId(R.id.editText)).check(matches(HintMatcher.withHint(hintText)));
    }

    /**
     * Same as above but using a {@link org.hamcrest.Matcher} as the argument.
     */
    @SuppressWarnings("unchecked")
    @Test
    public void hint_endsWith() {
        // This check will probably fail if the app is localized and the language is changed. Avoid
        // string literals in code!
        onView(withId(R.id.editText)).check(matches(HintMatcher.withHint(anyOf(
                endsWith(COFFEE_ENDING), endsWith(COFFEE_INVALID_ENDING)))));
    }

    @Test
    public void editText_canBeTypedInto() {
        onView(withId(R.id.editText))
                .perform(typeText(mValidStringToBeTyped), closeSoftKeyboard())
                .check(matches(withText(mValidStringToBeTyped)));
    }

    @Test
    public void validation_resultIsOneOfTheValidStrings() {
        // Type a valid string and click on the button.
        onView(withId(R.id.editText))
                .perform(typeText(mValidStringToBeTyped), closeSoftKeyboard());
        onView(withId(R.id.button)).perform(click());

        // Check that the correct sign is displayed.
        onView(withId(R.id.inputValidationSuccess)).check(matches(isDisplayed()));
        onView(withId(R.id.inputValidationError)).check(matches(not(isDisplayed())));
    }

    @Test
    public void validation_resultHasCorrectEnding() {
        // Type a string with a valid ending and click on the button.
        onView(withId(R.id.editText))
                .perform(typeText(mStringWithValidEnding), closeSoftKeyboard());
        onView(withId(R.id.button)).perform(click());

        // Check that the correct sign is displayed.
        onView(withId(R.id.inputValidationSuccess)).check(matches(isDisplayed()));
        onView(withId(R.id.inputValidationError)).check(matches(not(isDisplayed())));
    }

    @Test
    public void validation_resultIsIncorrect() {
        // Type a valid string and click on the button.
        onView(withId(R.id.editText))
                .perform(typeText(INVALID_STRING_TO_BE_TYPED), closeSoftKeyboard());
        onView(withId(R.id.button)).perform(click());

        // Check that the correct sign is displayed.
        onView(withId(R.id.inputValidationError)).check(matches(isDisplayed()));
        onView(withId(R.id.inputValidationSuccess)).check(matches(not(isDisplayed())));
    }
}