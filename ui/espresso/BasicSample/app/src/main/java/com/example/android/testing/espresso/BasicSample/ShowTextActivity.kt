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
package com.example.android.testing.espresso.BasicSample

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import com.google.common.base.Strings

/**
 * A simple [Activity] that shows a message.
 */
class ShowTextActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_text)

        // Get the message from the Intent.
        val intent = intent
        val message = Strings.nullToEmpty(intent.getStringExtra(KEY_EXTRA_MESSAGE))

        // Show message.
        (findViewById<View>(R.id.show_text_view) as TextView).text = message
    }

    companion object {
        // The name of the extra data sent through an {@link Intent}.
        const val KEY_EXTRA_MESSAGE = "com.example.android.testing.espresso.basicsample.MESSAGE"

        /**
         * Creates an [Intent] for [ShowTextActivity] with the message to be displayed.
         * @param context the [Context] where the [Intent] will be used
         * @param message a [String] with text to be displayed
         * @return an [Intent] used to start [ShowTextActivity]
         */
        fun newStartIntent(context: Context?, message: String?): Intent {
            val newIntent = Intent(context, ShowTextActivity::class.java)
            newIntent.putExtra(KEY_EXTRA_MESSAGE, message)
            return newIntent
        }
    }
}
