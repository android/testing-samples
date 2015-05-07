/*
 * Copyright 2015, The Android Open Source Project
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

package com.example.android.testing.espresso.basicsamplebundled.tests;

import junit.framework.TestCase;

/**
 * Unfortunately this is required by Eclipse to run JUnit4 style tests.
 *
 * <p>
 * This workaround tricks Eclipse into thinking there are actual tests to run. To find out if any
 * tests are available, Eclipse looks at classes which extend from {@link TestCase} or
 * {@link TestSuite}. As JUnit4 tests do not extend from {@link TestCase} anymore this pre-check
 * fails and Eclipse will not even attempt to start the runner.
 *
 * <p>
 * Once Eclipse detects this TestCase, the AndroidJUnitRunner will be able to detect the rest of the
 * tests.
 */
public class EnableJUnit4InEclipse extends TestCase {
    public void testDummy() {};
}
