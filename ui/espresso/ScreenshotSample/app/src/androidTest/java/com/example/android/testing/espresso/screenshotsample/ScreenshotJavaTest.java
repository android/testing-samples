package com.example.android.testing.espresso.screenshotsample;

import static androidx.test.core.app.DeviceCapture.takeScreenshot;
import static androidx.test.core.graphics.BitmapStorage.writeToTestStorage;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.captureToBitmap;
import static androidx.test.espresso.matcher.ViewMatchers.isRoot;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import android.graphics.Bitmap;

import androidx.test.espresso.action.ViewActions;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;
import org.junit.runner.RunWith;

import java.io.IOException;

/**
 * Equivalent of {@link ScreenshotTest} for java.
 */
@RunWith(AndroidJUnit4.class)
public class ScreenshotJavaTest {
    // a handy JUnit rule that stores the method name
    @Rule
    public TestName nameRule = new TestName();

    @Rule
    public ActivityScenarioRule<MainActivity> activityScenarioRule =
            new ActivityScenarioRule<>(MainActivity.class);

    /**
     * Captures and saves an image of the entire {@link MainActivity} contents.
     */
    @Test
    public void saveActivityBitmap() throws IOException {
        onView(isRoot()).perform(captureToBitmap(new ViewActions.BitmapReceiver() {
            @Override
            public void onBitmapCaptured(Bitmap bitmap) {
                try {
                    writeToTestStorage(bitmap, ScreenshotJavaTest.class.getSimpleName() + "_" + nameRule.getMethodName());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }));
    }

    /**
     * Captures and saves an image of the 'Hello world' view.
     */
    @Test
    public void saveViewBitmap() throws IOException {
        onView(withText("Hello World!")).perform(captureToBitmap(new ViewActions.BitmapReceiver() {
            @Override
            public void onBitmapCaptured(Bitmap bitmap) {
                try {
                    writeToTestStorage(bitmap, ScreenshotJavaTest.class.getSimpleName() + "_" + nameRule.getMethodName());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }));


    }

    /**
     * Captures and saves an image of the entire device screen to storage.
     */
    @Test
    public void saveDeviceScreenBitmap() throws IOException {
        writeToTestStorage(takeScreenshot(), getClass().getSimpleName() + "_" + nameRule.getMethodName());
    }
}
