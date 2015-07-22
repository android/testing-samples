package com.example.android.testing.espresso.CustomMatcherSample;

/**
 * @author jorge
 * @since 22/07/15
 */

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.ActivityInstrumentationTestCase2;
import android.test.suitebuilder.annotation.LargeTest;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

/**
 * Tests for {@link MainActivity} showcasing the use of custom matchers (see
 * {@link AlphaMatcher#withAlpha}).
 */
@RunWith(AndroidJUnit4.class) @LargeTest public class AlphaMatchersTest {

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
  @Rule public ActivityTestRule<MainActivity> mActivityRule =
      new ActivityTestRule<>(MainActivity.class);

  @Test public void preparationModes_textHintHasBeenDisplayed() {
    // Perform click on the preparation modes button
    onView(withId(R.id.preparationModesButton)).perform(click());

    // Check that the view has been displayed to the user by checking its alpha
    onView(withId(R.id.preparationsHintText)).check(matches(AlphaMatcher.withAlpha(1f)));
  }
}