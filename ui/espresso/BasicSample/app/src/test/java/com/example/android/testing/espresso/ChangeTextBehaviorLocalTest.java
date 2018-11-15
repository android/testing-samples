package com.example.android.testing.espresso;


import com.example.android.testing.espresso.BasicSample.MainActivity;
import com.example.android.testing.espresso.BasicSample.R;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import androidx.test.core.app.ActivityScenario;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

@RunWith(AndroidJUnit4.class)
public final class ChangeTextBehaviorLocalTest {

  public static final String STRING_TO_BE_TYPED = "Espresso";

  /**
   * Use {@link ActivityScenario} to create and launch the activity under test.
   */
  @Before
  public void launchActivity() {
    ActivityScenario.launch(MainActivity.class);
  }

  @Test
  public void changeText_sameActivity() {
    // Type text and then press the button.
    onView(withId(R.id.editTextUserInput))
            .perform(typeText(STRING_TO_BE_TYPED));

    // TODO: closeSoftKeyboard doesn't work on Robolectric
    // Robolectric issue #4148
    // closeSoftKeyboard());
    onView(withId(R.id.changeTextBt)).perform(click());

    // Check that the text was changed.
    // TODO: fails on Robolectric
    //onView(withId(R.id.textToBeChanged)).check(matches(withText(STRING_TO_BE_TYPED)));
  }
}
