# Basic sample for UiAutomator

*If you are new to UiAutomator, try this sample first.*


UiAutomator is fun because it can generate multiple inputs in any app on Android.
Please try first the following example, which is a simple app with tests that are written for itself.

Although UiAutomator only works with at least Android 4.3 (API 18), one can without any trouble
write a test for an app compiled with any lower API version, as long as the tests themselves
are being run on a API 18+ device.

This fact has interesting consequences:

- it allows the test app to be compliled quick, because the main app won't have to be compiled every time the tests are run.
- it allows white box testing, for the source code of the app to be tested is not necessary for tests to be written nor run.


This project uses the Gradle build system.
To test it using the command line, simple type:

- gradle connectedAndroidTest

To try it with Android Studio follow these steps:

1. Download the project code, preferably using `git clone`.
1. Open the Android SDK Manager (*Tools* Menu | *Android*) and make sure you have installed the *Android testing support library Repository* under *Extras*.
1. In Android Studio, select *File* | *Open...* and point to the `./build.gradle` file.
1. Check out the relevant code:
    * The application under test is located in `src/main/java`
    * Tests are in `src/androidTest/java`
1. Create the test configuration with a custom runner: `android.support.test.runner.AndroidJUnitRunner`
    * Open *Run* menu | *Edit Configurations*
    * Add a new *Android Tests* configuration
    * Choose a module
    * Add a *Specific instrumentation runner*: `android.support.test.runner.AndroidJUnitRunner`
1. Run the newly created configuration

The application will be started on the device/emulator and a series of actions will be performed automatically.

If you are using Android Studio, the *Run* window will show the test results.
