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

package com.example.android.testing.espresso.web.BasicSample;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import android.content.Intent;
import android.support.test.espresso.web.sugar.Web;
import android.support.test.espresso.web.webdriver.DriverAtoms;
import android.support.test.espresso.web.webdriver.Locator;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.ActivityInstrumentationTestCase2;
import android.test.suitebuilder.annotation.LargeTest;
import android.webkit.WebView;

import static android.support.test.espresso.web.assertion.WebViewAssertions.webMatches;
import static android.support.test.espresso.web.sugar.Web.onWebView;
import static android.support.test.espresso.web.webdriver.DriverAtoms.clearElement;
import static android.support.test.espresso.web.webdriver.DriverAtoms.findElement;
import static android.support.test.espresso.web.webdriver.DriverAtoms.getText;
import static android.support.test.espresso.web.webdriver.DriverAtoms.webClick;
import static org.hamcrest.Matchers.containsString;

/**
 * Basic sample that shows the usage of Espresso web showcasing {@link Web#onWebView()} API.
 * <p>
 * The sample has a simple layout which contains a single {@link WebView}. The HTML page displays
 * a form with an input tag and buttons to submit the form.
 */
@LargeTest
@RunWith(AndroidJUnit4.class)
public class WebViewActivityTest {

    private static final String MACCHIATO = "Macchiato";
    private static final String DOPPIO = "Doppio";

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
    public ActivityTestRule<WebViewActivity> mActivityRule = new ActivityTestRule<WebViewActivity>(
            WebViewActivity.class, false, false) {
        @Override
        protected void afterActivityLaunched() {
            // Technically we do not need to do this - WebViewActivity has javascript turned on.
            // Other WebViews in your app may have javascript turned off, however since the only way
            // to automate WebViews is through javascript, it must be enabled.
            onWebView().forceJavascriptEnabled();
        }
    };

    @Test
    public void typeTextInInput_clickButton_SubmitsForm() {
        // Lazily launch the Activity with a custom start Intent per test
        mActivityRule.launchActivity(withWebFormIntent());

        // Selects the WebView in your layout. If you have multiple WebViews you can also use a
        // matcher to select a given WebView, onWebView(withId(R.id.web_view)).
        onWebView()
                // Find the input element by ID
                .withElement(findElement(Locator.ID, "text_input"))
                // Clear previous input
                .perform(clearElement())
                // Enter text into the input element
                .perform(DriverAtoms.webKeys(MACCHIATO))
                // Find the submit button
                .withElement(findElement(Locator.ID, "submitBtn"))
                // Simulate a click via javascript
                .perform(webClick())
                // Find the response element by ID
                .withElement(findElement(Locator.ID, "response"))
                // Verify that the response page contains the entered text
                .check(webMatches(getText(), containsString(MACCHIATO)));
    }

    @Test
    public void typeTextInInput_clickButton_ChangesText() {
        // Lazily launch the Activity with a custom start Intent per test
        mActivityRule.launchActivity(withWebFormIntent());

        // Selects the WebView in your layout. If you have multiple WebViews you can also use a
        // matcher to select a given WebView, onWebView(withId(R.id.web_view)).
        onWebView()
                // Find the input element by ID
                .withElement(findElement(Locator.ID, "text_input"))
                // Clear previous input
                .perform(clearElement())
                // Enter text into the input element
                .perform(DriverAtoms.webKeys(DOPPIO))
                // Find the change text button.
                .withElement(findElement(Locator.ID, "changeTextBtn"))
                // Click on it.
                .perform(webClick())
                // Find the message element by ID
                .withElement(findElement(Locator.ID, "message"))
                // Verify that the text is displayed
                .check(webMatches(getText(), containsString(DOPPIO)));
    }

    /**
     * @return start {@link Intent} for the simple web form URL.
     */
    private static Intent withWebFormIntent() {
        Intent basicFormIntent = new Intent();
        basicFormIntent.putExtra(WebViewActivity.KEY_URL_TO_LOAD, WebViewActivity.WEB_FORM_URL);
        return basicFormIntent;
    }

}
