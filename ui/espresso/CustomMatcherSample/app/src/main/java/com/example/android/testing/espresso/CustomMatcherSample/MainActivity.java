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

package com.example.android.testing.espresso.CustomMatcherSample;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.VisibleForTesting;
import android.view.View;
import android.widget.EditText;

import java.util.Arrays;
import java.util.List;

/**
 * Asks the user for a coffee preparation and shows if it is valid.
 * <p>Valid inputs are anything ending in "coffee" or strings listed in
 * {@link MainActivity#COFFEE_PREPARATIONS} The check is always case-insensitive.
 * </p>
 */
public class MainActivity extends Activity implements View.OnClickListener {

    @VisibleForTesting
    public static final List<String> COFFEE_PREPARATIONS =
            Arrays.asList("Espresso", "Latte", "Mocha", "Café con leche", "Cold brew");

    @VisibleForTesting
    public static final String VALID_ENDING = "coffee";

    private EditText mInputText;
    private View mSuccessView;
    private View mErrorView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Sets the listener for the button.
        findViewById(R.id.button).setOnClickListener(this);

        // Get references to the EditText and views showing the result.
        mInputText = (EditText) findViewById(R.id.editText);
        mSuccessView = findViewById(R.id.inputValidationSuccess);
        mErrorView = findViewById(R.id.inputValidationError);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.button) {
            // The View to display depends on whether the input is valid or not.
            final String inputText = mInputText.getText().toString();

            // Validate the input and show the result.
            showResult(validateText(inputText));
        }
    }

    private void showResult(boolean isValidResult) {
        mSuccessView.setVisibility(isValidResult ? View.VISIBLE : View.GONE);
        mErrorView.setVisibility(isValidResult ? View.GONE : View.VISIBLE);
    }

    private static boolean validateText(String inputText) {
        // Every input ending in VALID_ENDING will return true.
        if (inputText.toLowerCase().endsWith(VALID_ENDING)) {
            return true;
        }

        // Check if the string is in the list.
        for (String preparation : COFFEE_PREPARATIONS) {
            if (preparation.equalsIgnoreCase(inputText)) {
                return true;
            }
        }
        return false;
    }
}
