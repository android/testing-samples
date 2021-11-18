/*
 * Copyright 2020, The Android Open Source Project
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

package com.example.android.testing.androidtestorchestratorsample;

import androidx.test.platform.graphics.HardwareRendererCompat;
import junit.framework.TestSuite;

import org.junit.Before;
import org.junit.Test;
import org.junit.internal.builders.AllDefaultPossibilitiesBuilder;
import org.junit.runner.RunWith;

import androidx.test.core.app.ActivityScenario;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;
import androidx.test.runner.AndroidJUnitRunner;

import static androidx.test.core.app.ApplicationProvider.getApplicationContext;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

/**
 * JUnit4 Ui Tests for {@link CalculatorActivity} using the {@link AndroidJUnitRunner} with the
 * Android Test Orchestrator.
 * This class uses the JUnit4 syntax for tests.
 * <p>
 * With the new AndroidJUnit runner you can run both JUnit3 and JUnit4 tests in a single test
 * suite. The {@link AndroidRunnerBuilder} which extends JUnit's
 * {@link AllDefaultPossibilitiesBuilder} will create a single {@link
 * TestSuite} from all tests and run them.
 */
@RunWith(AndroidJUnit4.class)
@LargeTest
public class CalculatorInstrumentationTest {

    /**
     * Use {@link ActivityScenario} to create and launch of the activity.
     */
    @Before
    public void launchActivity() {
        ActivityScenario.launch(CalculatorActivity.class);
    }

    @Test
    public void noOperandShowsComputationError() {
        final String expectedResult = getApplicationContext().getString(R.string.computationError);
        onView(withId(R.id.operation_add_btn)).perform(click());
        onView(withId(R.id.operation_result_text_view)).check(matches(withText(expectedResult)));
    }

    @Test
    public void typeOperandsAndPerformAddOperation() {
        performOperation(R.id.operation_add_btn, "16.0", "16.0", "32.0");
    }

    @Test
    public void typeOperandsAndPerformSubOperation() {
        performOperation(R.id.operation_sub_btn, "32.0", "16.0", "16.0");
    }

    @Test
    public void typeOperandsAndPerformDivOperation() {
        performOperation(R.id.operation_div_btn, "128.0", "16.0", "8.0");
    }

    @Test
    public void divZeroForOperandTwoShowsError() {
        final String expectedResult = getApplicationContext().getString(R.string.computationError);
        performOperation(R.id.operation_div_btn, "128.0", "0.0", expectedResult);
    }

    @Test
    public void typeOperandsAndPerformMulOperation() {
        performOperation(R.id.operation_mul_btn, "16.0", "16.0", "256.0");
    }

    private void performOperation(int btnOperationResId, String operandOne,
            String operandTwo, String expectedResult) {
        // Type the two operands in the EditText fields
        onView(withId(R.id.operand_one_edit_text)).perform(typeText(operandOne),
                closeSoftKeyboard());
        onView(withId(R.id.operand_two_edit_text)).perform(typeText(operandTwo),
                closeSoftKeyboard());

        // Click on a given operation button
        onView(withId(btnOperationResId)).perform(click());

        // Check the expected test is displayed in the Ui
        onView(withId(R.id.operation_result_text_view)).check(matches(withText(expectedResult)));
    }

}
