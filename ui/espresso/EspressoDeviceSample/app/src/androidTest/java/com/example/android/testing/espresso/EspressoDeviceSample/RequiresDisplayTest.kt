/*
 * Copyright 2022, The Android Open Source Project
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
package com.example.android.testing.espresso.EspressoDeviceSample

import androidx.test.espresso.device.filter.RequiresDisplay
import androidx.test.espresso.device.sizeclass.HeightSizeClass
import androidx.test.espresso.device.sizeclass.WidthSizeClass
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Test
import org.junit.runner.RunWith

/*
 * Illustrates usage of @RequiresDisplay API to filter tests based on display attributes such a screen size.
 */
@RunWith(AndroidJUnit4::class)
class RequiresDisplayTest {
    @Test
    fun shouldAlwaysRun() {}

    @RequiresDisplay(
        widthSizeClass = WidthSizeClass.Companion.WidthSizeClassEnum.COMPACT,
        heightSizeClass = HeightSizeClass.Companion.HeightSizeClassEnum.COMPACT
    )
    @Test
    fun testOnDevicesWithCompactWidthAndHeight() {}

    @RequiresDisplay(
        widthSizeClass = WidthSizeClass.Companion.WidthSizeClassEnum.COMPACT,
        heightSizeClass = HeightSizeClass.Companion.HeightSizeClassEnum.MEDIUM
    )
    @Test
    fun testOnDevicesWithCompactWidthAndMediumHeight() {}

    @RequiresDisplay(
        widthSizeClass = WidthSizeClass.Companion.WidthSizeClassEnum.MEDIUM,
        heightSizeClass = HeightSizeClass.Companion.HeightSizeClassEnum.COMPACT
    )
    @Test
    fun testOnDevicesWithMediumWidthAndCompactHeight() {}

    @RequiresDisplay(
        widthSizeClass = WidthSizeClass.Companion.WidthSizeClassEnum.COMPACT,
        heightSizeClass = HeightSizeClass.Companion.HeightSizeClassEnum.EXPANDED
    )
    @Test
    fun testOnDevicesWithCompactWidthAndExpandedHeight() {}

    @RequiresDisplay(
        widthSizeClass = WidthSizeClass.Companion.WidthSizeClassEnum.EXPANDED,
        heightSizeClass = HeightSizeClass.Companion.HeightSizeClassEnum.COMPACT
    )
    @Test
    fun testOnDevicesWithExpandedWidthAndCompactHeight() {}

    @RequiresDisplay(
        widthSizeClass = WidthSizeClass.Companion.WidthSizeClassEnum.MEDIUM,
        heightSizeClass = HeightSizeClass.Companion.HeightSizeClassEnum.MEDIUM
    )
    @Test
    fun testOnDevicesWithMediumWidthAndHeight() {}

    @RequiresDisplay(
        widthSizeClass = WidthSizeClass.Companion.WidthSizeClassEnum.EXPANDED,
        heightSizeClass = HeightSizeClass.Companion.HeightSizeClassEnum.MEDIUM,
    )
    @Test
    fun testOnDevicesWithExpandedWidthAndMediumHeight() {}


    @RequiresDisplay(
        widthSizeClass = WidthSizeClass.Companion.WidthSizeClassEnum.MEDIUM,
        heightSizeClass = HeightSizeClass.Companion.HeightSizeClassEnum.EXPANDED
    )
    @Test
    fun testOnDevicesWithMediumWidthAndExpandedHeight() {}

    @RequiresDisplay(
        widthSizeClass = WidthSizeClass.Companion.WidthSizeClassEnum.EXPANDED,
        heightSizeClass = HeightSizeClass.Companion.HeightSizeClassEnum.EXPANDED
    )
    @Test
    fun testOnDevicesWithExpandedWidthAndHeight() {}
}