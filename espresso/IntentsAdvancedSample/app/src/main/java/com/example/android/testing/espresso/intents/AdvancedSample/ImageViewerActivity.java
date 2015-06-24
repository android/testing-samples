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

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.VisibleForTesting;
import android.view.View;
import android.widget.ImageView;

/**
 * Captures an image and displays a thumbnail of the result.
 */
public class ImageViewerActivity extends Activity {

    @VisibleForTesting
    protected static final String KEY_IMAGE_DATA = "data";

    private static final int REQUEST_IMAGE_CAPTURE = 1;

    private ImageView mImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_viewer);
        mImageView = (ImageView) findViewById(R.id.imageView);
    }

    private void dispatchTakePictureIntent() {
        // Open the camera to take a photo.
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }

    public void onOpenCamera(View view) {
        dispatchTakePictureIntent();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // If an image is received, display it on the ImageView.
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            if (extras == null || !extras.containsKey(KEY_IMAGE_DATA)) {
                return;
            }
            Bitmap imageBitmap = (Bitmap) extras.get(KEY_IMAGE_DATA);
            mImageView.setImageBitmap(imageBitmap);
        }
    }
}