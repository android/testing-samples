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

package com.example.android.testing.espresso.BasicSample;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.VisibleForTesting;

/**
 * This a placeholder Activity for a contacts screen. This activity is never opened and does not
 * contain any real contact data for keeping this sample simple and focused.
 */
public class ContactsActivity extends Activity {

    static final String KEY_PHONE_NUMBER = "key_phone_number";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);
        setResult(Activity.RESULT_OK, createResultData("896-745-231"));
        finish();
    }

    @VisibleForTesting
    static Intent createResultData(String phoneNumber) {
        final Intent resultData = new Intent();
        resultData.putExtra(KEY_PHONE_NUMBER, phoneNumber);
        return resultData;
    }
}
