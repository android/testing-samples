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
package com.example.android.testing.espresso.MultiWindowSample;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

/**
 * An {@link android.app.Activity} that shows an editable text and gives suggestions to the user.
 */
public class SuggestActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.suggest_activity);

        setUpAutoCompleteTextView();
    }

    /**
     * Creates an adapter and sets it to an {@link AutoCompleteTextView} to enable suggestions.
     */
    private void setUpAutoCompleteTextView() {
        String[] completions = getResources().getStringArray(R.array.bodies_of_water);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_dropdown_item_1line,
                completions);

        AutoCompleteTextView autoComplete =
                (AutoCompleteTextView) findViewById(R.id.auto_complete_text_view);
        autoComplete.setAdapter(adapter);
    }
}
