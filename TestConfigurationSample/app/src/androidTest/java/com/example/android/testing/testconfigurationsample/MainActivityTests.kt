/*
 * Copyright 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.android.testing.testconfigurationsample

import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityTests {
    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun sampleTest1() {
        // Add instrumented tests here
        runBlocking { delay(10000) }
        assertTrue(true)
    }

    @Test
    fun sampleTest2() {
        // Add instrumented tests here
        runBlocking { delay(10000) }
        assertTrue(true)
    }

    @Test
    fun sampleTest3() {
        // Add instrumented tests here
        runBlocking { delay(10000) }
        assertTrue(true)
    }

    @Test
    fun sampleTest4() {
        // Add instrumented tests here
        runBlocking { delay(10000) }
        assertTrue(true)
    }

    /**
     * When you find an issue with a specific device or API level,
     * you can create an annotation for it and add it to your test.
     * In your CI setup you can then run tests with these annotations
     * on a specific Gradle Managed Virtual Device.
     *
     * See the Github Actions setup of this project for an example.
     */
    @Test @TestDeviceApi26
    fun regressionTestKnownIssueApi26() {
        // Add instrumented tests here
        runBlocking { delay(10000) }
        assertTrue(true)
    }
}
