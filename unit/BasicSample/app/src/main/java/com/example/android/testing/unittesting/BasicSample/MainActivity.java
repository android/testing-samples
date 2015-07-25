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

package com.example.android.testing.unittesting.BasicSample;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Calendar;

/**
 * An {@link Activity} that represents an input form page where the user can provide his name, date
 * of birth and email address. The personal information can be saved to {@link SharedPreferences}
 * by clicking a button.
 */
public class MainActivity extends Activity {

    // Logger for this class.
    private static final String TAG = "MainActivity";

    // The helper that manages writing to SharedPreferences.
    private SharedPreferencesHelper mSharedPreferencesHelper;

    // The input field where the user enters his name.
    private EditText mNameText;

    // The date picker where the user enters his date of birth.
    private DatePicker mDobPicker;

    // The input field where the user enters his email.
    private EditText mEmailText;

    // The validator for the email input field.
    private EmailValidator mEmailValidator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Shortcuts to input fields.
        mNameText = (EditText) findViewById(R.id.userNameInput);
        mDobPicker = (DatePicker) findViewById(R.id.dateOfBirthInput);
        mEmailText = (EditText) findViewById(R.id.emailInput);

        // Setup field validators.
        mEmailValidator = new EmailValidator();
        mEmailText.addTextChangedListener(mEmailValidator);

        // Instantiate a SharedPreferencesHelper.
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        mSharedPreferencesHelper = new SharedPreferencesHelper(sharedPreferences);

        // Fill input fields from data retrieved from the SharedPreferences.
        populateUi();
    }

    /**
     * Initialize all fields from the personal info saved in the SharedPreferences.
     */
    private void populateUi() {
        SharedPreferenceEntry sharedPreferenceEntry;
        sharedPreferenceEntry = mSharedPreferencesHelper.getPersonalInfo();
        mNameText.setText(sharedPreferenceEntry.getName());
        Calendar dateOfBirth = sharedPreferenceEntry.getDateOfBirth();
        mDobPicker.init(dateOfBirth.get(Calendar.YEAR), dateOfBirth.get(Calendar.MONTH),
                dateOfBirth.get(Calendar.DAY_OF_MONTH), null);
        mEmailText.setText(sharedPreferenceEntry.getEmail());
    }


    /**
     * Called when the "Save" button is clicked.
     */
    public void onSaveClick(View view) {
        // Don't save if the fields do not validate.
        if (!mEmailValidator.isValid()) {
            mEmailText.setError("Invalid email");
            Log.w(TAG, "Not saving personal information: Invalid email");
            return;
        }

        // Get the text from the input fields.
        String name = mNameText.getText().toString();
        Calendar dateOfBirth = Calendar.getInstance();
        dateOfBirth.set(mDobPicker.getYear(), mDobPicker.getMonth(), mDobPicker.getDayOfMonth());
        String email = mEmailText.getText().toString();

        // Create a Setting model class to persist.
        SharedPreferenceEntry sharedPreferenceEntry =
                new SharedPreferenceEntry(name, dateOfBirth, email);

        // Persist the personal information.
        boolean isSuccess = mSharedPreferencesHelper.savePersonalInfo(sharedPreferenceEntry);
        if (isSuccess) {
            Toast.makeText(this, "Personal information saved", Toast.LENGTH_LONG).show();
            Log.i(TAG, "Personal information saved");
        } else {
            Log.e(TAG, "Failed to write personal information to SharedPreferences");
        }
    }

    /**
     * Called when the "Revert" button is clicked.
     */
    public void onRevertClick(View view) {
        populateUi();
        Toast.makeText(this, "Personal information reverted", Toast.LENGTH_LONG).show();
        Log.i(TAG, "Personal information reverted");
    }
}
