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

import static org.junit.Assert.assertEquals;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.SmallTest;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;


/**
 * JUnit4 unit tests for the calculator logic.
 */
@RunWith(AndroidJUnit4.class)
@SmallTest
public class CalculatorTest {

    private Calculator mCalculator;

    @Before
    public void setUp() {
        mCalculator = new Calculator();
    }

    @Test
    public void addTwoNumbers() {
        double resultAdd = mCalculator.add(1d, 1d);
        assertEquals(resultAdd, 2d, 0);
    }

    @Test
    public void subTwoNumbers() {
        double resultSub = mCalculator.sub(1d, 1d);
        assertEquals(resultSub, 0d, 0);
    }

    @Test
    public void subWorksWithNegativeResult() {
        double resultSub = mCalculator.sub(1d, 17d);
        assertEquals(resultSub, -16d, 0);
    }

    @Test
    public void divTwoNumbers() {
        double resultDiv = mCalculator.div(32d, 2d);
        assertEquals(resultDiv, 16d, 0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void divDivideByZeroThrows() {
        mCalculator.div(32d, 0d);
    }

    @Test
    public void mulTwoNumbers() {
        double resultMul = mCalculator.mul(32d, 2d);
        assertEquals(resultMul, 64d, 0);
    }

}