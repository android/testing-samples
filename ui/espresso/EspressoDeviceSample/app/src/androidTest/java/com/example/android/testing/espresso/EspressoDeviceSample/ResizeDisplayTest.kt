package com.example.android.testing.espresso.EspressoDeviceSample

import androidx.test.espresso.device.DeviceInteraction.Companion.setDisplaySize
import androidx.test.espresso.device.EspressoDevice.Companion.onDevice
import androidx.test.espresso.device.filter.RequiresDisplay
import androidx.test.espresso.device.rules.DisplaySizeRule
import androidx.test.espresso.device.sizeclass.HeightSizeClass
import androidx.test.espresso.device.sizeclass.WidthSizeClass
import androidx.test.espresso.device.sizeclass.WidthSizeClass.Companion.WidthSizeClassEnum
import androidx.test.espresso.device.sizeclass.HeightSizeClass.Companion.HeightSizeClassEnum
import androidx.test.ext.junit.rules.activityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import androidx.test.espresso.device.sizeclass.WidthSizeClass.Companion.WidthSizeClassEnum.EXPANDED as EXPANDED1

/*
 * Illustrates usage of [onDevice] API to change the display size.
 */
@RunWith(AndroidJUnit4::class)
class ResizeDisplayTest {

    @get:Rule(order = 1) var activityScenarioRule = activityScenarioRule<MainActivity>()

    // Test rule for restoring device to its starting display size when a test case finishes
    @get:Rule(order = 2) var displaySizeRule: DisplaySizeRule = DisplaySizeRule()

    @Test
    fun resizeWindow_compact() {
        onDevice().setDisplaySize(
            widthSizeClass = WidthSizeClass.COMPACT,
            heightSizeClass = HeightSizeClass.COMPACT
        )
        // Verify visual attributes or state restoration
    }

    /**
     * Setting the display size to EXPANDED would fail in small devices, so the [RequiresDisplay]
     * annotation prevents this test from being run on devices outside the EXPANDED buckets.
     */
    @RequiresDisplay(
        widthSizeClass = WidthSizeClassEnum.EXPANDED,
        heightSizeClass = HeightSizeClassEnum.EXPANDED
    )
    @Test
    fun resizeWindow_expanded() {
        onDevice().setDisplaySize(
            widthSizeClass = WidthSizeClass.EXPANDED,
            heightSizeClass = HeightSizeClass.EXPANDED
        )
        // Verify visual attributes or state restoration
    }
}
