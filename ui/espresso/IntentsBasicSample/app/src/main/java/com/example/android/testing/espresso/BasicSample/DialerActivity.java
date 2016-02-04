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

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Simple Dialer Activity which shows an {@link EditText} field to enter a phone number. Upon
 * pressing the call button the number entered in the input field is send to the native Android
 * Dialer app via {@link Intent#ACTION_CALL}.
 *
 * <p>
 * Furthermore this Activity contains a pick number button to starts dummy contacts activity to
 * demonstrate Intent stubbing.
 */
public class DialerActivity extends Activity {

    private static final int REQUEST_CODE_PICK = 16;

    private EditText mCallerNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialer);
        mCallerNumber = (EditText) findViewById(R.id.edit_text_caller_number);
    }

    public void onCall(View view) {
        boolean hasCallPhonePermission = ContextCompat.checkSelfPermission(this,
            Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED;

        if (hasCallPhonePermission)
            startActivity(createCallIntentFromNumber());
        else
            Toast.makeText(this, R.string.warning_call_phone_permission, Toast.LENGTH_SHORT).show();
    }

    public void onPickContact(View view) {
        final Intent pickContactIntent = new Intent(this, ContactsActivity.class);
        startActivityForResult(pickContactIntent, REQUEST_CODE_PICK);
    }

    private Intent createCallIntentFromNumber() {
        final Intent intentToCall = new Intent(Intent.ACTION_CALL);
        String number = mCallerNumber.getText().toString();
        intentToCall.setData(Uri.parse("tel:" + number));
        return intentToCall;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_CODE_PICK) {
            if (resultCode == RESULT_OK) {
                mCallerNumber.setText(data.getExtras()
                        .getString(ContactsActivity.KEY_PHONE_NUMBER));
            }
        }
    }
}