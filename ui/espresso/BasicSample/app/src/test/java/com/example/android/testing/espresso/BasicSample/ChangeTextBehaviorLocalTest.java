package com.example.android.testing.espresso.BasicSample;


import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static androidx.test.ext.truth.content.IntentSubject.assertThat;

import androidx.test.espresso.intent.Intents;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import com.google.common.collect.Iterables;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public final class ChangeTextBehaviorLocalTest {

  public static final String STRING_TO_BE_TYPED = "Espresso";

  /**
   * Use {@link ActivityScenarioRule} to create and launch the activity under test.
   */
  @Rule public ActivityScenarioRule<MainActivity> activityScenarioRule =
      new ActivityScenarioRule<MainActivity>(MainActivity.class);

  @Before
  public void intentsInit() {
    // initialize Espresso Intents capturing
    Intents.init();
  }

  @After
  public void intentsTeardown() {
    // release Espresso Intents capturing
    Intents.release();
  }

  @Test
  public void changeText_sameActivity() {
    // Type text and then press the button.
    onView(withId(R.id.editTextUserInput))
            .perform(typeText(STRING_TO_BE_TYPED), closeSoftKeyboard());

    onView(withId(R.id.changeTextBt)).perform(click());

    // Check that the text was changed.
    onView(withId(R.id.textToBeChanged)).check(matches(withText(STRING_TO_BE_TYPED)));
  }

  @Test
  public void changeText_newActivity() {
    // Type text and then press the button.
    onView(withId(R.id.editTextUserInput)).perform(typeText(STRING_TO_BE_TYPED),
        closeSoftKeyboard());
    onView(withId(R.id.activityChangeTextBtn)).perform(click());

    // An intent is fired to launch a different Activity. Robolectric doesn't currently
    // support launching a new Activity, so use Espresso Intents to verify intent was sent
    assertThat(Iterables.getOnlyElement(Intents.getIntents())).hasComponentClass(
        ShowTextActivity.class);
  }
}
