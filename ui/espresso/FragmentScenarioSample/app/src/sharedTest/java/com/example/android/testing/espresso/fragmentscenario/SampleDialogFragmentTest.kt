package com.example.android.testing.espresso.fragmentscenario

import androidx.fragment.app.testing.launchFragment
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.RootMatchers.isDialog
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.common.truth.Truth.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.LooperMode

/**
 * A test using the androidx.test unified API, which can execute on an Android device or locally using Robolectric.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
@LooperMode(LooperMode.Mode.PAUSED)
class SampleDialogFragmentTest {

  @Test
  fun launchDialogFragmentAndVerifyUI() {
    // Use launchFragment to launch the dialog fragment in a dialog.
    val scenario = launchFragment<SampleDialogFragment>()

    scenario.onFragment { fragment ->
      assertThat(fragment.dialog).isNotNull()
      assertThat(fragment.dialog!!.isShowing).isTrue()
    }

    // Now use espresso to look for the fragment's text view and verify it is displayed.
    Espresso.onView(ViewMatchers.withId(R.id.textView)).inRoot(isDialog())
      .check(ViewAssertions.matches(ViewMatchers.withText("I am a fragment")));
  }

  @Test
  fun launchDialogFragmentEmbeddedToHostActivityAndVerifyUI() {
    // Use launchFragmentInContainer to inflate a dialog fragment's view into Activity's content view.
    val scenario = launchFragmentInContainer<SampleDialogFragment>()

    scenario.onFragment { fragment ->
      // Dialog is not created because you use launchFragmentInContainer and the view is inflated
      // into the Activity's content view.
      assertThat(fragment.dialog).isNull()
    }

    // Now use espresso to look for the fragment's text view and verify it is displayed.
    Espresso.onView(ViewMatchers.withId(R.id.textView))
      .check(ViewAssertions.matches(ViewMatchers.withText("I am a fragment")));
  }
}
