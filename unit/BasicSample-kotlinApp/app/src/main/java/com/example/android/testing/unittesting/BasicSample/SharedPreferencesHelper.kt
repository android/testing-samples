/*
 * Copyright (C) 2017 The Android Open Source Project
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

package com.example.android.testing.unittesting.BasicSample

import android.content.SharedPreferences
import java.util.Calendar

/**
 * Helper class to manage access to [SharedPreferences].
 *
 * @param sharedPreferences The injected [SharedPreferences] that will be used in this DAO.
 */
class SharedPreferencesHelper(private val sharedPreferences: SharedPreferences) {

    /**
     * Retrieves the [SharedPreferenceEntry] containing the user's personal information from
     * [SharedPreferences].
     *
     * @return the Retrieved [SharedPreferenceEntry].
     */
    // Get data from the SharedPreferences.
    // Create and fill a SharedPreferenceEntry model object.
    fun getPersonalInfo(): SharedPreferenceEntry {
        val name = sharedPreferences.getString(KEY_NAME, "")
        val dobMillis = sharedPreferences.getLong(KEY_DOB, Calendar.getInstance().timeInMillis)
        val dateOfBirth = Calendar.getInstance().apply { timeInMillis = dobMillis }
        val email = sharedPreferences.getString(KEY_EMAIL, "")
        return SharedPreferenceEntry(name, dateOfBirth, email)
    }

    /**
     * Saves the given [SharedPreferenceEntry] that contains the user's settings to
     * [SharedPreferences].
     *
     * @param sharedPreferenceEntry contains data to save to [SharedPreferences].
     * @return `true` if writing to [SharedPreferences] succeeded, `false` otherwise.
     */
    fun savePersonalInfo(sharedPreferenceEntry: SharedPreferenceEntry): Boolean {
        // Start a SharedPreferences transaction.
        val editor = sharedPreferences.edit().apply() {
            putString(KEY_NAME, sharedPreferenceEntry.name)
            putLong(KEY_DOB, sharedPreferenceEntry.dateOfBirth.timeInMillis)
            putString(KEY_EMAIL, sharedPreferenceEntry.email)
        }

        // Commit changes to SharedPreferences.
        return editor.commit()
    }

    companion object {
        // Keys for saving values in SharedPreferences.
        internal val KEY_NAME = "key_name"
        internal val KEY_DOB = "key_dob_millis"
        internal val KEY_EMAIL = "key_email"
    }
}
