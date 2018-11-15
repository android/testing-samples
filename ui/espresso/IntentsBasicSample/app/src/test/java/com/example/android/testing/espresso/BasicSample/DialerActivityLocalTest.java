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

package com.example.android.testing.espresso.BasicSample;

import android.Manifest;
import android.app.Activity;
import android.app.Application;
import android.content.Intent;
import android.net.Uri;

import com.google.common.collect.Iterables;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import androidx.test.core.app.ApplicationProvider;
import androidx.test.espresso.intent.Intents;
import androidx.test.espresso.intent.rule.IntentsTestRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import static android.app.Instrumentation.ActivityResult;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.Intents.intending;
import static androidx.test.espresso.intent.matcher.ComponentNameMatchers.hasShortClassName;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasAction;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasData;
import static androidx.test.espresso.intent.matcher.IntentMatchers.isInternal;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static androidx.test.ext.truth.content.IntentSubject.assertThat;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.core.AllOf.allOf;
import static org.robolectric.Shadows.shadowOf;

@RunWith(AndroidJUnit4.class)
public class DialerActivityLocalTest {

    //private static final String VALID_PHONE_NUMBER = "123-345-6789";
    // TODO: '-' not recognized in robolectric #4129
    private static final String VALID_PHONE_NUMBER = "1233456789";

    private static final Uri INTENT_DATA_PHONE_NUMBER = Uri.parse("tel:" + VALID_PHONE_NUMBER);


    /**
     * A JUnit {@link Rule @Rule} to init and release Espresso Intents before and after each
     * test run.
     * <p>
     * Rules are interceptors which are executed for each test method and will run before
     * any of your setup code in the {@link Before @Before} method.
     * <p>
     * This rule is based on {@link ActivityTestRule} and will create and launch the activity
     * for you and also expose the activity under test.
     */
    @Rule
    public IntentsTestRule<DialerActivity> mActivityRule = new IntentsTestRule<>(
            DialerActivity.class);

    @Before
    public void stubAllExternalIntents() {
        // By default Espresso Intents does not stub any Intents. Stubbing needs to be setup before
        // every test run. In this case all external Intents will be blocked.
        intending(not(isInternal())).respondWith(new ActivityResult(Activity.RESULT_OK, null));
    }

    @Before
    public void grantPhonePermission() {
        // TODO: convert to GrantPermissionRule #4133
        Application application = ApplicationProvider.getApplicationContext();
        shadowOf(application).grantPermissions(Manifest.permission.CALL_PHONE);
    }

    @Test
    public void typeNumber_ValidInput_InitiatesCall() {
        // Types a phone number into the dialer edit text field and presses the call button.
        onView(withId(R.id.edit_text_caller_number))
                .perform(typeText(VALID_PHONE_NUMBER));
        // , closeSoftKeyboard() doesn't work on robo #4148
        onView(withId(R.id.button_call_number)).perform(click());

        // Verify that an intent to the dialer was sent with the correct action, phone
        // number and package. Think of Intents intended API as the equivalent to Mockito's verify.
        intended(allOf(
                hasAction(Intent.ACTION_CALL),
                hasData(INTENT_DATA_PHONE_NUMBER)));
    }

    /**
     * Duplicate typeNumber_ValidInput_InitiatesCall, but using truth assertions
     */
    @Test
    public void typeNumber_ValidInput_InitiatesCall_truth() {
        // Types a phone number into the dialer edit text field and presses the call button.
        onView(withId(R.id.edit_text_caller_number))
                .perform(typeText(VALID_PHONE_NUMBER));
        // , closeSoftKeyboard() doesn't work on robo #4148
        onView(withId(R.id.button_call_number)).perform(click());

        // Verify that an intent to the dialer was sent with the correct action, phone
        // number and package.
        Intent receivedIntent = Iterables.getOnlyElement(Intents.getIntents());
        assertThat(receivedIntent).hasAction(Intent.ACTION_CALL);
        assertThat(receivedIntent).hasData(INTENT_DATA_PHONE_NUMBER);
    }

    @Test
    @Ignore // doesn't work on Robo #4150
    public void pickContactButton_click_SelectsPhoneNumber() {
        // Stub all Intents to ContactsActivity to return VALID_PHONE_NUMBER. Note that the Activity
        // is never launched and result is stubbed.
        intending(hasComponent(hasShortClassName(".ContactsActivity")))
                .respondWith(new ActivityResult(Activity.RESULT_OK,
                        ContactsActivity.createResultData(VALID_PHONE_NUMBER)));

        // Click the pick contact button.
        onView(withId(R.id.button_pick_contact)).perform(click());

        // Check that the number is displayed in the UI.
        onView(withId(R.id.edit_text_caller_number))
                .check(matches(withText(VALID_PHONE_NUMBER)));
    }
}
