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
 * When this test is executed via gradle managed devices, the saved image files will be stored at
 * build/outputs/managed_device_android_test_additional_output/debugAndroidTest/managedDevice/nexusOneApi30/
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
      .writeToTestStorage("${javaClass.simpleName}_${nameRule.methodName}")
  }

  /**
   * Captures and saves an image of the 'Hello world' view.
   */
  @Test
  @Throws(IOException::class)
  fun saveViewBitmap() {
    onView(withText("Hello World!"))
      .captureToBitmap()
      .writeToTestStorage("${javaClass.simpleName}_${nameRule.methodName}")
  }

  /**
   * Captures and saves an image of the entire device screen to storage.
   */
  @Test
  @Throws(IOException::class)
  fun saveDeviceScreenBitmap() {
    takeScreenshot()
      .writeToTestStorage("${javaClass.simpleName}_${nameRule.methodName}")
  }
}
