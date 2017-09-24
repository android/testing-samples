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

package com.example.android.testing.robolectric.BasicSample;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.android.controller.ActivityController;
import org.robolectric.annotation.Config;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.robolectric.Shadows.shadowOf;


/**
 * Robolectric tests for {@link MainActivity}.
 */
@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class)
public class MainActivityTest {

    private static final String STRING_TO_BE_TYPED = "Robolectric";

    private MainActivity activity;

    @Before
    public void setUp() {
        activity = Robolectric.setupActivity(MainActivity.class);
    }

    @Test
    public void changeText_sameActivity() {
        // Type text and then press the button.
        TextView editTextUserInput = activity.findViewById(R.id.editTextUserInput);
        Button changeTextBt = activity.findViewById(R.id.changeTextBt);
        editTextUserInput.setText(STRING_TO_BE_TYPED);
        changeTextBt.performClick();

        // Check that the text was changed.
        TextView textToBeChanged = activity.findViewById(R.id.textToBeChanged);
        Assert.assertThat(textToBeChanged.getText().toString(),
                Matchers.equalTo(STRING_TO_BE_TYPED));
    }

    @Test
    public void changeText_newActivity() {
        // Type text and then press the button.
        TextView editTextUserInput = activity.findViewById(R.id.editTextUserInput);
        Button activityChangeTextBtn = activity.findViewById(R.id.activityChangeTextBtn);
        editTextUserInput.setText(STRING_TO_BE_TYPED);
        activityChangeTextBtn.performClick();

        // Verify that intent is sent with the correct message.
        Intent expectedIntent = new Intent(activity, ShowTextActivity.class);
        Intent actualIntent = shadowOf(activity).getNextStartedActivity();
        assertThat(actualIntent.getComponent(), equalTo(expectedIntent.getComponent()));
        assertThat(actualIntent.getStringExtra(ShowTextActivity.KEY_EXTRA_MESSAGE),
                equalTo(STRING_TO_BE_TYPED));
    }

    @Test
    public void createActivityFromIntent() {
        Intent intent = new Intent(RuntimeEnvironment.application, MainActivity.class);
        Robolectric.buildActivity(MainActivity.class, intent).setup().get();
    }

    @Test
    public void pauseResumeActivity() {
        ActivityController<MainActivity> controller = Robolectric.buildActivity(MainActivity.class);
        controller.setup().get();
        controller.pause().resume();
    }

    @Test
    public void recreateActivity() {
        Bundle bundle = new Bundle();
        ActivityController<MainActivity> controller = Robolectric.buildActivity(MainActivity.class);
        controller.setup().get();
        controller.saveInstanceState(bundle)
                .pause()
                .stop()
                .destroy();
        controller = Robolectric.buildActivity(MainActivity.class)
                .create(bundle)
                .start()
                .restoreInstanceState(bundle)
                .resume()
                .visible();
        controller.get();
    }
}