/*
 * Copyright 2015 Google Inc. All rights reserved.
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

package com.example.android.testing.unittesting.basicunitandroidtest;

import android.app.Activity;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Lets the user add lines to a multi-line log. When the device is rotated, the state is saved
 * and restored.
 */
public class MainActivity extends Activity {

    private static final String KEY_HISTORY_DATA = "KEY_HISTORY_DATA";

    LogHistory mLogHistory;
    boolean mIsHistoryEmpty = true;
    private TextView mHistoryTextView;
    private DateFormat mSimpleDateFormatter;

    /**
     * Called when the user wants to append an entry to the history.
     */
    public void updateHistory(View view) {
        // Get the text to add and timestamp.
        EditText editText = (EditText) view.getRootView().findViewById(R.id.editText);
        CharSequence textToAdd = editText.getText();
        long timestamp = System.currentTimeMillis();

        // Show it back to the user.
        appendEntryToView(textToAdd.toString(), timestamp);

        // Update the history.
        mLogHistory.addEntry(textToAdd.toString(), timestamp);

        // Reset the EditText.
        editText.setText("");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mLogHistory = new LogHistory();

        mHistoryTextView = ((TextView) findViewById(R.id.history));
        mSimpleDateFormatter = new SimpleDateFormat("HH:mm:ss:SSS", Locale.getDefault());

        if (savedInstanceState != null) {
            // We've got a past state, apply it to the UI.
            mLogHistory = savedInstanceState.getParcelable(KEY_HISTORY_DATA);
            for (Pair<String, Long> entry : mLogHistory.getData()) {
                appendEntryToView(entry.first, entry.second);
            }
        }
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable(KEY_HISTORY_DATA, mLogHistory);
    }

    private void appendEntryToView(String text, long timestamp) {
        Date date = new Date(timestamp);
        // Add a newline if needed or clear the text view (to get rid of the hint).
        if (!mIsHistoryEmpty) {
            mHistoryTextView.append("\n");
        } else {
            mHistoryTextView.setText("");
        }
        // Add the representation of the new entry to the text view.
        mHistoryTextView.append(String.format("%s [%s]", text, mSimpleDateFormatter.format(date)));

        mIsHistoryEmpty = false;
    }
}
