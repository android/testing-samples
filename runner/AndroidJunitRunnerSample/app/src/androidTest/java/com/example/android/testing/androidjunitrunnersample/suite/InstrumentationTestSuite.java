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

package com.example.android.testing.androidjunitrunnersample.suite;

import com.example.android.testing.androidjunitrunnersample.CalculatorInstrumentationTest;
import com.example.android.testing.androidjunitrunnersample.OperationHintInstrumentationTest;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * Runs all Junit3 and Junit4 Instrumentation tests.
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({CalculatorInstrumentationTest.class, OperationHintInstrumentationTest.class})
public class InstrumentationTestSuite {}
