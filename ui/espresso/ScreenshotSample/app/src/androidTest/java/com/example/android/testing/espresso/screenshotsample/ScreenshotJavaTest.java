package com.example.android.testing.espresso.screenshotsample;

import static androidx.test.core.app.DeviceCapture.takeScreenshot;
import static androidx.test.core.graphics.BitmapStorage.writeToTestStorage;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.matcher.ViewMatchers.isRoot;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static androidx.test.espresso.screenshot.ViewInteractionCapture.captureToBitmap;

import android.view.View;

import androidx.concurrent.futures.ResolvableFuture;
import androidx.test.core.app.ActivityScenario;
import androidx.test.core.view.ViewCapture;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;
import org.junit.runner.RunWith;

import java.io.IOException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

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
        writeToTestStorage(captureToBitmap(onView(isRoot())), nameRule.getMethodName());
    }

    /**
     * Captures and saves an image of the 'Hello world' view.
     */
    @Test
    public void saveViewBitmap() throws IOException {
        writeToTestStorage(captureToBitmap(onView(withText("Hello World!"))), nameRule.getMethodName());
    }

    /**
     * Captures and saves an image of the entire device screen to storage.
     */
    @Test
    public void saveDeviceScreenBitmap() throws IOException {
        writeToTestStorage(takeScreenshot(), nameRule.getMethodName());
    }
}
