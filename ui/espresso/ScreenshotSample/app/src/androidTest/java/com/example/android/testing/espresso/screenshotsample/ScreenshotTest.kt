package com.example.android.testing.espresso.screenshotsample

import androidx.test.core.app.takeScreenshot
import androidx.test.core.graphics.writeToTestStorage
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.matcher.ViewMatchers.isRoot
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.espresso.screenshot.captureToBitmap
import androidx.test.ext.junit.rules.activityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestName
import org.junit.runner.RunWith
import java.io.IOException

/*
 * Illustrates usage of APIs to capture a bitmap from view and saving it to test storage.
 *
 * The exact path will vary based on android API version, but the saved files can be retrieved via
 * Device File Explorer at a path like
 * /storage/emulated/0/googletest/test_outputfiles
 *
 * A future Android Gradle Plugin version should auto-retrieve these files from the device onto the
 * host.
 */
@RunWith(AndroidJUnit4::class)
class ScreenshotTest {

  // a handy JUnit rule that stores the method name, so it can be used to generate unique
  // screenshot files per test method
  @get:Rule
  var nameRule = TestName()

  @get:Rule
  val activityScenarioRule = activityScenarioRule<MainActivity>()

  /**
   * Captures and saves an image of the entire [MainActivity] contents.
   */
  @Test
  @Throws(IOException::class)
  fun saveActivityBitmap() {
    onView(isRoot())
      .captureToBitmap()
      .writeToTestStorage(nameRule.methodName)
  }

  /**
   * Captures and saves an image of the 'Hello world' view.
   */
  @Test
  @Throws(IOException::class)
  fun saveViewBitmap() {
    onView(withText("Hello World!"))
      .captureToBitmap()
      .writeToTestStorage(nameRule.methodName)
  }

  /**
   * Captures and saves an image of the entire device screen to storage.
   */
  @Test
  @Throws(IOException::class)
  fun saveDeviceScreenBitmap() {
    takeScreenshot()
      .writeToTestStorage(nameRule.methodName)
  }
}
