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

package com.example.android.testing.androidjunitrunnersample;

import junit.framework.TestSuite;

import org.junit.internal.builders.AllDefaultPossibilitiesBuilder;

import android.support.test.runner.AndroidJUnitRunner;
import android.test.ActivityInstrumentationTestCase2;
import android.test.suitebuilder.annotation.LargeTest;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static com.example.android.testing.androidjunitrunnersample.HintMatcher.withHint;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

/**
 * JUnit3 Ui Tests for {@link CalculatorActivity} using the {@link AndroidJUnitRunner}. This class
 * uses the Junit3 syntax for tests and extends {@link ActivityInstrumentationTestCase2}.
 * <p>
 * With the new AndroidJUnit runner you can run both JUnit3 and JUnit4 tests in a single test
 * test suite. The {@link AndroidRunnerBuilder} which extends JUnit's {@link
 * AllDefaultPossibilitiesBuilder} will create a single {@link TestSuite} from all tests and run
 * them.
 * <p>
 * ActivityInstrumentationTestCase2 will be deprecated soon. Please use {@link ActivityTestRule}
 * when writing new tests. For an example on how to use {@link ActivityTestRule} please see
 * {@link CalculatorInstrumentationTest}.
 */
@LargeTest
public class OperationHintLegacyInstrumentationTest
        extends ActivityInstrumentationTestCase2<CalculatorActivity> {

    private CalculatorActivity mActivity;

    public OperationHintLegacyInstrumentationTest() {
        super(CalculatorActivity.class);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();

        // Espresso does not start the Activity for you we need to do this manually here.
        mActivity = getActivity();
    }

    public void testPreconditions() {
        assertThat(mActivity, notNullValue());
    }

    public void testEditText_OperandOneHint() {
        String operandOneHint = mActivity.getString(R.string.type_operand_one_hint);
        onView(withId(R.id.operand_one_edit_text)).check(matches(withHint(operandOneHint)));
    }

    public void testEditText_OperandTwoHint() {
        String operandTwoHint = mActivity.getString(R.string.type_operant_two_hint);
        onView(withId(R.id.operand_two_edit_text)).check(matches(withHint(operandTwoHint)));
    }

}
