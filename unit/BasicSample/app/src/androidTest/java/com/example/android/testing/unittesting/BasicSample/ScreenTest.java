package com.example.android.testing.unittesting.BasicSample;

import android.support.test.espresso.ViewInteraction;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.action.ViewActions.scrollTo;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

@LargeTest @RunWith(AndroidJUnit4.class) public class ScreenTest {

  @Rule public ActivityTestRule<MainActivity> mActivityTestRule =
      new ActivityTestRule<>(MainActivity.class);

  @Test public void saveScreenTest1() {
    ViewInteraction editText = onView(withId(R.id.userNameInput));
    editText.perform(scrollTo(), replaceText("JSBerrocoso"), closeSoftKeyboard());

    ViewInteraction editText2 = onView(withId(R.id.emailInput));
    editText2.perform(scrollTo(), replaceText("jsbs87@gmail.com"), closeSoftKeyboard());

    ViewInteraction button = onView(allOf(withId(R.id.saveButton), withText("Save")));
    button.perform(scrollTo(), click());
  }

  @Test public void revertScreenTest() {

    ViewInteraction editText4 = onView(allOf(withId(R.id.userNameInput)));
    editText4.perform(scrollTo(), replaceText("Santi"), closeSoftKeyboard());

    ViewInteraction editText5 =
        onView(allOf(withId(R.id.emailInput), withText("jsbs87@gmail.com")));
    editText5.perform(scrollTo(), click());

    ViewInteraction editText6 =
        onView(allOf(withId(R.id.emailInput)));
    editText6.perform(scrollTo(), replaceText("jsberrocoso@gmail.com"), closeSoftKeyboard());

    ViewInteraction button3 = onView(allOf(withId(R.id.revertButton), withText("Revert")));
    button3.perform(scrollTo(), click());
  }
}
