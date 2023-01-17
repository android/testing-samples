package com.example.android.testing.espresso.BasicSample

import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.ext.truth.content.IntentSubject
import com.google.common.collect.Iterables
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ChangeTextBehaviorLocalTest {
    /**
     * Use [ActivityScenarioRule] to create and launch the activity under test.
     */
    @Rule
    var activityScenarioRule = ActivityScenarioRule(MainActivity::class.java)

    @Before
    fun intentsInit() {
        // initialize Espresso Intents capturing
        Intents.init()
    }

    @After
    fun intentsTeardown() {
        // release Espresso Intents capturing
        Intents.release()
    }

    @Test
    fun changeText_sameActivity() {
        // Type text and then press the button.
        Espresso.onView(ViewMatchers.withId(R.id.editTextUserInput))
            .perform(ViewActions.typeText(STRING_TO_BE_TYPED), ViewActions.closeSoftKeyboard())
        Espresso.onView(ViewMatchers.withId(R.id.changeTextBt)).perform(ViewActions.click())

        // Check that the text was changed.
        Espresso.onView(ViewMatchers.withId(R.id.textToBeChanged)).check(
            ViewAssertions.matches(
                ViewMatchers.withText(
                    STRING_TO_BE_TYPED
                )
            )
        )
    }

    @Test
    fun changeText_newActivity() {
        // Type text and then press the button.
        Espresso.onView(ViewMatchers.withId(R.id.editTextUserInput)).perform(
            ViewActions.typeText(STRING_TO_BE_TYPED),
            ViewActions.closeSoftKeyboard()
        )
        Espresso.onView(ViewMatchers.withId(R.id.activityChangeTextBtn)).perform(ViewActions.click())

        // An intent is fired to launch a different Activity. Robolectric doesn't currently
        // support launching a new Activity, so use Espresso Intents to verify intent was sent
        IntentSubject.assertThat(Iterables.getOnlyElement(Intents.getIntents())).hasComponentClass(
            ShowTextActivity::class.java
        )
    }

    companion object {
        const val STRING_TO_BE_TYPED = "Espresso"
    }
}
