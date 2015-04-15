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

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.ActivityInstrumentationTestCase2;
import android.test.suitebuilder.annotation.LargeTest;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.anyOf;
import static org.hamcrest.Matchers.endsWith;
import static org.hamcrest.Matchers.not;

/**
 * Tests for {@link MainActivity} showcasing the use of custom matchers (see
 * {@link HintMatcher#withHint}).
 */
@RunWith(AndroidJUnit4.class)
@LargeTest
public class HintMatchersTest {

    private static final String INVALID_STRING_TO_BE_TYPED = "Earl Grey";

    private static final String COFFEE_ENDING = "coffee?";

    private static final String COFFEE_INVALID_ENDING = "tea?";

    /**
     * A JUnit {@link Rule @Rule} to launch your activity under test. This is a replacement
     * for {@link ActivityInstrumentationTestCase2}.
     * <p>
     * Rules are interceptors which are executed for each test method and will run before
     * any of your setup code in the {@link Before @Before} method.
     * <p>
     * {@link ActivityTestRule} will create and launch of the activity for you and also expose
     * the activity under test. To get a reference to the activity you can use
     * the {@link ActivityTestRule#getActivity()} method.
     */
    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule<>(
            MainActivity.class);

    // A valid string with a valid ending
    private String mStringWithValidEnding;

    // A valid string from the coffee preparations
    private String mValidStringToBeTyped;


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
        String hintText = mActivityRule.getActivity().getResources().getString(R.string.hint);

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