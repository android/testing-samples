package com.example.android.testing.espresso.EspressoDeviceSample

import androidx.test.espresso.Espresso.closeSoftKeyboard
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.device.DeviceInteraction.Companion.setBookMode
import androidx.test.espresso.device.DeviceInteraction.Companion.setClosedMode
import androidx.test.espresso.device.DeviceInteraction.Companion.setFlatMode
import androidx.test.espresso.device.DeviceInteraction.Companion.setTabletopMode
import androidx.test.espresso.device.EspressoDevice.Companion.onDevice
import androidx.test.espresso.device.action.ScreenOrientation
import androidx.test.espresso.device.action.setClosedMode
import androidx.test.espresso.device.action.setFlatMode
import androidx.test.espresso.device.action.setTabletopMode
import androidx.test.espresso.device.controller.DeviceMode.TABLETOP
import androidx.test.espresso.device.filter.RequiresDeviceMode
import androidx.test.espresso.device.rules.ScreenOrientationRule
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.activityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/*
 * Illustrates usage of [onDevice] API to change the mode of foldable devices.
 */
@RunWith(AndroidJUnit4::class)
class OnDeviceTest {

    @get:Rule var activityScenarioRule = activityScenarioRule<MainActivity>()

    @get:Rule val screenOrientationRule: ScreenOrientationRule =
        ScreenOrientationRule(ScreenOrientation.PORTRAIT)

    @Test
    @RequiresDeviceMode(TABLETOP)
    fun changeText_sameActivity() {

        onDevice().setBookMode()
        // Type text and then press the button.
        onView(withId(R.id.hello_tv)).check(matches(isDisplayed()))
        onDevice().setTabletopMode()
        onDevice().setClosedMode()
        onView(withId(R.id.hello_tv)).check(matches(isDisplayed()))
        onDevice().setFlatMode()
        onView(withId(R.id.hello_tv)).check(matches(isDisplayed()))
    }
}
