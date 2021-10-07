/*
 * Copyright 2014, The Android Open Source Project
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

package com.example.android.testing.androidjunitrunnersample;

import static androidx.test.core.app.ApplicationProvider.getApplicationContext;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static com.example.android.testing.androidjunitrunnersample.HintMatcher.withHint;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;
import androidx.test.runner.AndroidJUnitRunner;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Ui Tests for {@link CalculatorActivity} operation hints using the {@link AndroidJUnitRunner}.
 */
@LargeTest
@RunWith(AndroidJUnit4.class)
public class OperationHintInstrumentationTest {

    /**
     * Use {@link ActivityScenarioRule} to create and launch of the activity before each test, and close
     * it after each test.
     */
    @Rule
    public ActivityScenarioRule<CalculatorActivity> mActivityScenarioRule
            = new ActivityScenarioRule<>(CalculatorActivity.class);

    @Test
    public void testEditText_OperandOneHint() {
        String operandOneHint = getApplicationContext().getString(R.string.type_operand_one_hint);
        onView(withId(R.id.operand_one_edit_text)).check(matches(withHint(operandOneHint)));
    }

    @Test
    public void testEditText_OperandTwoHint() {
        String operandTwoHint = getApplicationContext().getString(R.string.type_operant_two_hint);
        onView(withId(R.id.operand_two_edit_text)).check(matches(withHint(operandTwoHint)));
    }
}
