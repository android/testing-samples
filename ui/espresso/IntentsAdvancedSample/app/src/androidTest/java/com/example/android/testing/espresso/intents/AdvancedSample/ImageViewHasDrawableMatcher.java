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

package com.example.android.testing.espresso.intents.AdvancedSample;

import androidx.test.espresso.matcher.BoundedDiagnosingMatcher;
import android.view.View;
import android.widget.ImageView;

import org.hamcrest.Description;

/**
 * A Matcher for Espresso that checks if an ImageView has a drawable applied to it.
 */
public class ImageViewHasDrawableMatcher {

    public static BoundedDiagnosingMatcher<View, ImageView> hasDrawable() {
        return new BoundedDiagnosingMatcher<View, ImageView>(ImageView.class) {
             @Override
            protected void describeMoreTo(Description description) {
                description.appendText("has drawable");
            }

            @Override
            protected boolean matchesSafely(ImageView imageView, Description mismatchDescription) {
                return imageView.getDrawable() != null;
            }
        };
    }
}
